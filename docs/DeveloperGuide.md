---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# AB-3 Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​      | I want to …​                                                  | So that I can…​                                                          |
|----------|--------------|---------------------------------------------------------------|--------------------------------------------------------------------------|
| `* * *`  | new user     | see usage instructions                                        | refer to instructions when I forget how to use the App                   |
| `* * *`  | travel agent | add a new contact                                             | manage customer and service information effectively                      |
| `* * *`  | travel agent | delete a contact                                              | remove outdated or invalid contacts from the system                      |
| `* * *`  | travel agent | add a new trip                                                | consolidate all trip-related information in one place                    |
| `* * *`  | travel agent | delete a trip                                                 | remove outdated or irrelevant trips from the system                      |
| `* * *`  | travel agent | mark a trip as ongoing                                        | track and manage active trips efficiently                                |
| `* * *`  | travel agent | mark a trip as completed                                      | keep track of trips that no longer require management                    |
| `* * *`  | travel agent | tag contacts                                                  | clearly differentiate between customers and services                     |
| `* * *`  | travel agent | search for contacts and trips                                 | quickly locate the information I need                                    |
| `* * *`  | travel agent | export customer and service information                       | share data with colleagues easily                                        |
| `* * *`  | travel agent | add notes to customer profiles or trips                       | track special requests or important details                              |
| `* *`    | travel agent | restrict adding service contacts as trip members              | ensure only valid customer contacts are added to trips                   |
| `* *`    | travel agent | restrict adding customer contacts as trip location businesses | ensure only valid service contacts are added as trip location businesses |
| `*`      | travel agent | sort contacts by name                                         | locate a contact easily                                                  |
### Use cases

(For all use cases below, the **System** is the `Travel Agency Management System` and the **Actor** is the `Travel Agent`, unless specified otherwise)

**Use case: See Usage Instructions**

**MSS**

1.  Travel Agent requests to see usage instructions.
2.  System displays a list of available commands and their formats.
3.  Travel Agent reads the instructions.

    Use case ends.

**Extensions**

* 2a. The system fails to load the instructions.
    * 2a1. System displays an error message: "Unable to load usage instructions. Please try again later."
    * 2a2. Use case ends.

**Use case: Add a Contact**

**MSS**

1.  Travel Agent requests to add a new contact with details (name, phone number, email, address, and tag).
2.  System validates the contact details.
3.  System adds the contact to the contact list.
4.  System displays a success message: "New contact added: [Name]."

    Use case ends.

**Extensions**

* 2a. The contact details are invalid (e.g., invalid phone number or email format).
    * 2a1. System displays an error message: "Invalid command format. Correct format: addContact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…"
    * 2a2. Use case resumes at step 1.
* 2b. The contact already exists in the system (same name and phone number).
    * 2b1. System displays an error message: "This contact already exists in the system."
    * 2b2. Use case ends.

**Use case: Add a Trip**

**MSS**

1.  Travel Agent requests to add a new trip with details (date, customer index, accommodation, and itinerary).
2.  System validates the trip details.
3.  System adds the trip to the trip list.
4.  System displays a success message: "New trip added: [Date] for [Customer Name]."

    Use case ends.

**Extensions**

* 2a. The trip details are invalid (e.g., invalid date format or customer index).
    * 2a1. System displays an error message: "Invalid command format. Correct format: addTrip d/DATE c/CUSTOMER_INDEX a/ACCOMMODATION i/ITINERARY."
    * 2a2. Use case resumes at step 1.
* 2b. The trip already exists in the system (same date and customer).
    * 2b1. System displays an error message: "This trip already exists in the system."
    * 2b2. Use case ends.
* 2c. The customer index refers to a service contact (not a customer).
    * 2c1. System displays an error message: "Invalid customer index. Only customer contacts can be added as trip members."
    * 2c2. Use case resumes at step 1.
* 2d. The accommodation refers to a customer contact (not a service).
    * 2d1. System displays an error message: "Invalid accommodation. Only service contacts can be added as trip location businesses."
    * 2d2. Use case resumes at step 1.

**Use case: Delete a Contact**

**MSS**

1.  Travel Agent requests to list all contacts.
2.  System shows a list of contacts.
3.  Travel Agent requests to delete a specific contact by index.
4.  System deletes the contact.
5.  System displays a success message: "Contact deleted: [Name]."

    Use case ends.

**Extensions**

* 2a. The contact list is empty.
    * 2a1. System displays a message: "No contacts found."
    * 2a2. Use case ends.
* 3a. The given index is invalid.
    * 3a1. System displays an error message: "Invalid index. Please provide a valid index."
    * 3a2. Use case resumes at step 2.

**Use case: Delete a Trip**

**MSS**

1.  Travel Agent requests to list all trips.
2.  System shows a list of trips.
3.  Travel Agent requests to delete a specific trip by index.
4.  System deletes the trip.
5.  System displays a success message: "Trip deleted: [Date] for [Customer Name]."

    Use case ends.

**Extensions**

* 2a. The trip list is empty.
    * 2a1. System displays a message: "No trips found."
    * 2a2. Use case ends.
* 3a. The given index is invalid.
    * 3a1. System displays an error message: "Invalid index. Please provide a valid index."
    * 3a2. Use case resumes at step 2.

**Use case: Mark a Trip as Ongoing**

**MSS**

1.  Travel Agent requests to list all trips.
2.  System shows a list of trips.
3.  Travel Agent requests to mark a specific trip as ongoing by index.
4.  System marks the trip as ongoing.
5.  System displays a success message: "Trip marked as ongoing: [Date] for [Customer Name]."

    Use case ends.

**Extensions**

* 2a. The trip list is empty.
    * 2a1. System displays a message: "No trips found."
    * 2a2. Use case ends.
* 3a. The given index is invalid.
    * 3a1. System displays an error message: "Invalid index. Please provide a valid index."
    * 3a2. Use case resumes at step 2.

**Use case: Mark a Trip as Completed**

**MSS**

1.  Travel Agent requests to list all trips.
2.  System shows a list of trips.
3.  Travel Agent requests to mark a specific trip as completed by index.
4.  System marks the trip as completed.
5.  System displays a success message: "Trip marked as completed: [Date] for [Customer Name]."

    Use case ends.

**Extensions**

* 2a. The trip list is empty.
    * 2a1. System displays a message: "No trips found."
    * 2a2. Use case ends.
* 3a. The given index is invalid.
    * 3a1. System displays an error message: "Invalid index. Please provide a valid index."
    * 3a2. Use case resumes at step 2.

**Use case: Search for Contacts and Trips**

**MSS**

1.  Travel Agent requests to search for contacts or trips using a keyword.
2.  System searches for matching contacts and trips.
3.  System displays a list of matching results.

    Use case ends.

**Extensions**

* 2a. No matches are found.
    * 2a1. System displays a message: "No matches found."
    * 2a2. Use case ends.

**Use case: Export Customer and Service Information**

**MSS**

1.  Travel Agent requests to export customer and service information.
2.  System checks for existing data.
3.  System exports the data to a file (e.g., `contacts.csv`).
4.  System displays a success message: "Customer and service data exported to contacts.csv."

    Use case ends.

**Extensions**

* 2a. No data exists to export.
    * 2a1. System displays a message: "No data to export."
    * 2a2. Use case ends.
* 3a. The system fails to export the data.
    * 3a1. System displays an error message: "Failed to export data. Please try again later."
    * 3a2. Use case ends.

**Use case: Add Notes to Customer Profiles or Trips**

**MSS**

1.  Travel Agent requests to add a note to a customer profile or trip by specifying the index and note content.
2.  System validates the index and note content.
3.  System adds the note to the specified customer profile or trip.
4.  System displays a success message: "Note added: [Note Content]."

    Use case ends.

**Extensions**

* 2a. The index is invalid.
    * 2a1. System displays an error message: "Invalid index. Please provide a valid index."
    * 2a2. Use case resumes at step 1.
* 2b. The note content is empty.
    * 2b1. System displays an error message: "Note content cannot be empty."
    * 2b2. Use case resumes at step 1.

**Use case: Restrict Adding Service Contacts as Trip Members**

**MSS**

1.  Travel Agent requests to add a trip and specifies a customer index.
2.  System checks if the customer index refers to a service contact.
3.  System prevents the addition of the service contact as a trip member.
4.  System displays an error message: "Invalid customer index. Only customer contacts can be added as trip members."

    Use case ends.

**Extensions**

* 2a. The customer index refers to a valid customer contact.
    * 2a1. System proceeds to add the trip.
    * 2a2. Use case ends.

**Use case: Restrict Adding Customer Contacts as Trip Location Businesses**

**MSS**

1.  Travel Agent requests to add a trip and specifies an accommodation.
2.  System checks if the accommodation refers to a customer contact.
3.  System prevents the addition of the customer contact as a trip location business.
4.  System displays an error message: "Invalid accommodation. Only service contacts can be added as trip location businesses."

    Use case ends.

**Extensions**

* 2a. The accommodation refers to a valid service contact.
    * 2a1. System proceeds to add the trip.
    * 2a2. Use case ends.

**Use case: Sort Contacts by Name**

**MSS**

1.  Travel Agent requests to sort contacts by name.
2.  System sorts the contacts alphabetically by name.
3.  System displays the sorted list of contacts.

    Use case ends.

**Extensions**

* 2a. The contact list is empty.
    * 2a1. System displays a message: "No contacts found."
    * 2a2. Use case ends.
* 2b. The system fails to sort the contacts.
    * 2b1. System displays an error message: "Failed to sort contacts. Please try again later."
    * 2b2. Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `17` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
