---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# AB-3 User Guide

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a list of all available commands.

Format: `help`

Expected output:

```
Available commands:
 - help: Shows program usage instructions
 - addContact: Adds a new contact
 - addTrip: Adds a new trip
 - deleteContact: Removes a contact at a specified index
 - deleteTrip: Removes a trip at a specified index
 - editContact: Edits a contact at a specified index
 - listContact: Lists all contacts [can specify tag type]
 - listTrips: Lists all trips 
 - clear: Clear all contacts and trips from Ui
 - exit: Exits the program
```

### Adding a contact: `addContact`

Adds a contact to the address book.

Format: `addContact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A contact can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all contacts : `list`

Shows a list of all contacts in the address book.

Format: `list`

### Listing contacts : `listContact`

Shows a list of contacts in the address book.

Format: `listContact [customer/service]`

* Without specifying the optional parameter, all contacts will be displayed.
* By specifying the `[customer/service]` parameter, only contacts with the tag will be displayed.

### Editing a contact : `editContact`

Edits an existing contact in the address book.

Format: `editContact INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]… [nts/NOTE]​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be removed i.e adding of tags is not cumulative.
* You can remove all the contact's tags by typing `t/` without specifying any tags after it.

Examples:
*  `editContact 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
*  `editContact 2 n/Betsy Crower t/` Edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.

### Locating contacts by name: `find`

Finds contacts whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a contact : `deleteContact`

Deletes the specified contact from the address book.

Format: `deleteContact INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `deleteContact 2` deletes the 2nd contact in the address book.
* `find Betsy` followed by `deleteContact 1` deletes the 1st contact in the results of the `find` command.

### Deleting a trip : `deleteTrip`

Deletes the specified trip from the trip book.

Format: `deleteTrip INDEX`

* Deletes the trip at the specified `INDEX`.
* The index refers to the index number shown in the displayed trip list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `deleteTrip 2` deletes the 2nd trip in the address book.
* `find Betsy` followed by `deleteTrip 1` deletes the 1st trip in the results of the `find` command.

### Adding a trip : `addTrip`

Adds a trip to the address book.

Format: `addTrip n/NAME d/DATE c/CONTACT_INDEX l/LOCATION [t/TAG]…​ [nts/NOTE]`

* Adds a trip with the specified details.
* The CONTACT_INDEX refers to the index number of the contact in the displayed contact list.
* The date should be in the format of DD/MM/YYYY.
* You can add optional notes about the trip using the nts/ parameter.

Examples:
* `addTrip n/Business Meeting d/15/12/2023 c/1 l/Orchard Road t/important nts/Bring sales materials`
* `addTrip n/Site Visit d/22/01/2024 c/3 l/Jurong East t/service t/follow-up`

### Listing all trips : `listTrip`

Shows a list of all trips in the address book.

Format: `listTrip [DATE]`

* Without specifying the optional parameter, all trips will be displayed.
* By specifying the `[DATE]` parameter, only trips on that specific date will be displayed.
* The date should be in the format of DD/MM/YYYY.

### Editing a trip : `editTrip`

Edits an existing trip in the address book.

Format: `editTrip INDEX [n/NAME] [d/DATE] [c/CONTACT_INDEX] [l/LOCATION] [t/TAG]…​ [nts/NOTE]`

* Edits the trip at the specified `INDEX`. The index refers to the index number shown in the displayed trip list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the trip will be removed i.e adding of tags is not cumulative.
* You can remove all the trip's tags by typing `t/` without specifying any tags after it.

Examples:
* `editTrip 1 d/20/12/2023 l/Marina Bay Sands nts/Changed venue due to renovation` Edits the date, location and adds a note for the 1st trip.
* `editTrip 2 n/Follow-up Meeting c/2 t/urgent` Edits the name and contact of the 2nd trip and changes its tag to urgent.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit Contact**   | `editContact INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`editContact 2 n/James Lee e/jameslee@example.com`
**Delete Contact** | `deleteContact INDEX`<br> e.g., `deleteContact 3`
**Delete Trip** | `deleteTrip INDEX`<br> e.g., `deleteTrip 3`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`
**Add Trip** | `addTrip n/NAME d/DATE c/CONTACT_INDEX l/LOCATION [t/TAG]…​ [nts/NOTE]`<br> e.g., `addTrip n/Business Meeting d/15/12/2023 c/1 l/Orchard Road t/important nts/Bring sales materials`
**List Trip** | `listTrip [dd/MM/YYYY]`<br> e.g., `listTrip 15/12/2023`
**Edit Trip** | `editTrip INDEX [n/NAME] [d/DATE] [c/CONTACT_INDEX] [l/LOCATION] [t/TAG]…​ [nts/NOTE]`<br> e.g., `editTrip 1 d/20/12/2023 l/Marina Bay Sands nts/Changed venue due to renovation`
