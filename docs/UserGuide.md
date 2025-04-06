---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# TravelHub User Guide

TravelHub is a contact management app designed to help travel agents efficiently manage customer information and service details, such as addresses and contact information. Using a simple command-line interface, it supports **adding, deleting, tagging of contact profiles and trips**.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/AY2425S2-CS2103-F09-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar travelhub.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will list all the available commands.<br>
   Some example commands you can try:

   * `listContact` : Lists all contacts.

   * `addContact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `deleteContact 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts and trips.

   * `exit` : Exits the app.

1. Refer to the Features below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addContact n/NAME`, `NAME` is a parameter which can be used as `addContact n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/customer` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/customer`, `t/customer t/service` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Contact Parameters
A contact consists the following parameters: name, phone, email, address, tags and notes.
The parameters follow immediately after their corresponding prefixes and are useful for the `addContact` and `editContact` commands.

| Parameter | Prefix | Description |
|-----------|--------|-------------|
| `NAME`    | `n/`   | Specifies the name of the contact.<br><br>**Requirements:**<ul><li>Name is a mandatory parameter and cannot be empty.</li><li>Names can only contain alphanumeric characters, spaces, hyphens (-), apostrophes ('), and dots (.)</li></ul> |
| `PHONE`   | `p/`   | Specifies the phone number of the contact.<br><br>**Requirements:** <ul><li>Phone number is a mandatory parameter and cannot be empty.</li><li>Phone numbers should only contain numbers.</li><li>The `+` sign for country code should be omitted.</li><li>Phone numbers should be 3-17 digits long.</li></ul> |
| `EMAIL`   | `e/`   | Specifies the email of the contact <br><br>**Requirements:** <ul><li>**Contacts are uniquely identified by their email**</li><li>Email is a mandatory parameter and cannot be empty.</li><li>Emails should be of the format `local-part@domain`.</li><li>Local-part should contain only alphanumeric characters and these special characters, excluding the parentheses, (+_.-).</li><li>The local-part may not start or end with any special characters.</li><li>Special characters can only appear between alphanumeric characters and cannot be placed next to each other.</li><li>The domain should contain only alphanumeric characters, hyphens (-) and dots (.)</li><li>The domain must be at least 2 characters long</li><li>The domain must start and end with alphanumeric characters</li></ul> |
| `ADDRESS` | `a/`   | Specifies the address of the contact.<br><br> **Requirements:**<ul><li>Address is a mandatory parameter and cannot be empty.</li></ul> |
| `TAG`     | `t/`   | Specifies the tag of a customer.<br><br> **Requirements:** <ul><li>Tag is an optional parameter and can be omitted when adding a contact.</li><li>Tags can only be specified as `t\customer` or `t/service`</li><li>You may include both by specifying `t/customer t/service`</li></ul> |
| `NOTE`    | `nts/` | Specifies additional notes related to the contact.<br><br> **Requirements:** <ul><li>Note is an optional parameter and can be omitted when adding a contact.</li></ul> |

### Trip Parameters
A trip consists the following parameters: trip name, accommodation, itineraries date, customers, and notes.
The parameters follow immediately after their corresponding prefixes and are useful for the `addTrip` and `editTrip` commands.

| Parameter       | Prefix | Description |
|-----------------|--------|-------------|
| `NAME`          | `n/`   | Specifies the name of the trip.<br><br>**Requirements:**<ul><li>Name is a mandatory parameter and cannot be empty.</li><li>Trip names can only contain alphanumeric characters and spaces.</li></ul> |
| `ACCOMMODATION` | `acc/` | Specifies the accomodation for the trip.<br><br>**Requirements:**<ul><li>Accomodation is a mandatory parameter and cannot be empty.</li></ul> |
| `ITINERARY`     | `i/`   | Specifies the itinerary for the trip. <br><br>**Requirements:**<ul><li>Itinerary is a mandatory parameter and cannot be empty.</li></ul> |
| `DATE`          | `d/`   | Specifies the date of the trip. <br><br>**Requirements:**<ul><li>Date is a mandatory parameter and cannot be empty.</li><li>Date must follow the format `d/M/yyyy`.</li><li>A valid date allowed is between year 1950 and 2100 inclusive.</li></ul> |
| `CUSTOMER_NAME` | `c/`   | Specifies the name of the customers participating in the trip.<br><br> **Requirements:** <ul><li>Customer name is an optional parameter and can be omitted when adding a trip.</li><li>Customer name follows the requirements of the `NAME` parameter in contact.</li><li>You can specify multiple customer names by repeating the c/ prefix separated by a space, e.g., `c/John Doe c/Jane Doe c/Joe Doe`.</li></ul> |
| `NOTE`          | `nts/` | Specifies additional notes related to the trip.<br><br> **Requirements:** <ul><li>Note is an optional parameter and can be omitted when adding a trip.</li></ul> |

### Viewing help : `help`

Shows a list of all available commands.

Format: `help`

Expected output:

```
Available commands:
- help: Shows program usage instructions
- addContact: Adds a new contact
- addTrip: Adds a new trip with name, accommodation, itinerary, date, optional customer names and optional note
- deleteContact: Removes a contact at a specified index
- deleteTrip: Removes a trip at a specified index
- editContact: Edits a contact at a specified index
- editTrip: Edits a trip at a specified index
- listContact: Lists all contacts [can specify tag type]
- listTrip: Lists all trips 
- find: Find contacts whose names contain any of the given keywords
- clear: Clear all contacts and trips
- exit: Exits the program
```

### Locating contacts by name: `find`

Finds contacts whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only exact words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Listing contacts : `listContact`

Shows a list of contacts in the address book.

Format: `listContact [customer/service]`

* Without specifying the optional parameter, all contacts will be displayed.
* By specifying the `[customer/service]` parameter, only contacts with the tag will be displayed.

### Adding a contact: `addContact`

Adds a contact to the address book.

Format: `addContact n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]… [nts/NOTE]​`

<box type="tip" seamless>

**Tip:** Tags must be 'customer' or 'service' e.g., 't/customer t/service'.

A contact can have no tags, 1 tag or both the customer and service tag.

**Requirements:** Email must be unique across all contacts
</box>

Examples:
* `addContact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addContact n/XYZ Restaurant e/xyz_cuisine@example.com a/XYZ Street p/67654321 t/service nts/Serves western cuisine`
* `addContact n/Betty's Gift Shop e/betty_biz@example.com a/Sunshine Street 3 p/67654321 t/service t/customer`

### Editing a contact : `editContact`

Edits an existing contact in the address book.

Format: `editContact INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]… [nts/NOTE]​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be removed i.e adding of tags is not cumulative.
* You can remove all the contact's tags by typing `t/` without specifying any tags after it.
* You can remove all the contact's notes by typing `nts/` without specifying anything after it.

Examples:
*  `editContact 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
*  `editContact 2 n/Betsy Crower t/` Edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.

### Deleting a contact : `deleteContact`

Deletes the specified contact from the address book.

Format: `deleteContact INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listContact` followed by `deleteContact 2` deletes the 2nd contact in the address book.
* `find Betsy` followed by `deleteContact 1` deletes the 1st contact in the results of the `find` command.

### Listing all trips : `listTrip`

Shows a list of all trips in the trip book.

Format: `listTrip [DATE]`

* Without specifying the optional parameter, all trips will be displayed.
* By specifying the `[DATE]` parameter, only trips on that specific date will be displayed.
* The date should be in the format of DD/MM/YYYY.

### Adding a trip : `addTrip`

Adds a trip to the trip book.

Format: `addTrip n/NAME acc/ACCOMMODATION i/ITINERARY d/DATE [c/CUSTOMER_NAME]... [nts/NOTE]`

* Adds a trip with the specified details.
* The date should be in the format of DD/MM/YYYY.
* Customer names are optional. You can specify multiple customer names by using the c/ prefix multiple times.
* You can add optional notes about the trip using the nts/ prefix.

Examples:
* `addTrip n/Paris 2025 acc/Hotel Sunshine i/Visit Eiffel Tower; Eat baguette d/01/1/2025 c/Jane Doe c/John Doe nts/Customer prefers window seat`
* `addTrip n/Beach Vacation acc/Beach Resort i/Relax by the beach; Snorkeling d/15/3/2024 c/Alice Smith nts/All-inclusive package`
* `addTrip n/Business Conference acc/City Hotel i/Attend presentations; Networking d/10/5/2024 nts/Corporate rate applies`

### Editing a trip : `editTrip`

Edits an existing trip in the trip book.

Format: `editTrip INDEX [n/NAME] [acc/ACCOMMODATION] [i/ITINERARY] [d/DATE] [c/CUSTOMER_NAME]... [nts/NOTE]`

* Edits the trip at the specified `INDEX`. The index refers to the index number shown in the displayed trip list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing customer names, the existing customer names of the trip will be removed and replaced with the new ones.
* You can remove all customer names by not including any c/ prefixes.
* Customer names are optional.

Examples:
* `editTrip 1 acc/Grand Hotel i/Visit Louvre; Visit Seine River nts/Changed hotel due to availability` Edits the accommodation, itinerary, and adds a note for the 1st trip.
* `editTrip 2 n/London Trip 2025 c/Jane Doe c/Bob Smith` Edits the name and changes the customer names for the 2nd trip.

### Deleting a trip : `deleteTrip`

Deletes the specified trip from the trip book.

Format: `deleteTrip INDEX`

* Deletes the trip at the specified `INDEX`.
* The index refers to the index number shown in the displayed trip list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listTrip` followed by `deleteTrip 2` deletes the 2nd trip in the trip book.

### Clearing all entries : `clear`

Clears all contact and trip entries in the application.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook and TripBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Similarly TripBook data are saved automatically as a JSON file `[JAR file location]/data/tripbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook and TripBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook and TripBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook and TripBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Help**   | `help` |
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List Contact**   | `listContact [customer/service]` <br> e.g., `listContact service`
**Add Contact**    | `addContact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]… [nts/NOTE]​` <br> e.g., `addContact n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/customer`
**Edit Contact**   | `editContact INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]… [nts/NOTE]​`<br> e.g.,`editContact 2 n/James Lee e/jameslee@example.com`
**Delete Contact** | `deleteContact INDEX`<br> e.g., `deleteContact 3`
**List Trip** | `listTrip [dd/MM/YYYY]`<br> e.g., `listTrip 15/12/2023`
**Add Trip** | `addTrip n/NAME acc/ACCOMMODATION i/ITINERARY d/DATE [c/CUSTOMER_NAME]... [nts/NOTE]`<br> e.g., `addTrip n/Paris 2025 acc/Hotel Sunshine i/Visit Eiffel Tower; Eat baguette d/01/1/2025 c/Jane Doe c/John Doe nts/Customer prefers window seat`
**Edit Trip** | `editTrip INDEX [n/NAME] [acc/ACCOMMODATION] [i/ITINERARY] [d/DATE] [c/CUSTOMER_NAME]... [nts/NOTE]`<br> e.g., `editTrip 1 acc/Grand Hotel i/Visit Louvre; Visit Seine River nts/Changed hotel due to availability`
**Delete Trip** | `deleteTrip INDEX`<br> e.g., `deleteTrip 3`
**Clear**  | `clear`
**Exit**   | `exit`
