[![CI Status](https://github.com/AY2425S2-CS2103-F09-1/tp/actions/workflows/gradle.yml/badge.svg)](https://github.com/AY2425S2-CS2103-F09-1/tp/actions)

![Ui](docs/images/Ui.png)

# TravelHub


## Contents
* [Overview](#overview)
* [Features](#features)
* [Command Details](#command-details)
* [Command Summary](#command-summary)
* [Acknowledgments](#acknowledgments)


## Overview
This is TravelHub! TravelHub is a contact management app designed to help travel agents efficiently manage customer information and service details, such as addresses and contact information.
Using a simple command-line interface, it supports **adding, deleting, tagging of contact profiles and trips**.


## Features
TravelHub provides travel agents with tools to:
* Add and manage contacts
* Add trips with customers, services and itinerary
* Tag contacts for better organisation
* Add notes to customer profiles and trips keep track preferences and details
* Mark trips as completed/`ongoing

**Command Format**
* Words in `UPPER_CASE` are parameters you need to supply
* Items in square brackets `[...]` are optional
* Parameters labeled with `INDEX` refer to the position of the item in the displayed list


## Command Details

### 1. Contact Management

#### Add Contact: `addContact`
Adds a new contact (customer or service provider) to the system.

**Format**: `addContact n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]... [nts/NOTE]`

**Examples**:
* `addContact n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/customer t/service nts/Preferred contact method is email`
* `addContact n/Beach Resort p/12345678 e/resort@example.com a/456 Beach Road t/service nts/24-hour reception`

**Parameter Requirements**:
* `NAME`: Alphabetic characters, spaces, and hyphens allowed
* `PHONE`: Must be 8 digits
* `EMAIL`: Must follow standard email format (e.g., user@domain.com)
* `ADDRESS`: Any alphanumeric characters, spaces, and common punctuation marks
* `TAG`: Must be either 'customer' or 'service' (optional, can have both)
* `NOTE`: Any text (optional)

#### Delete Contact: `deleteContact`
Removes a contact from the system.

**Format**: `deleteContact INDEX`

**Example**: `deleteContact 1`

#### Tag Contact: `tagContact`
Tags a contact as either a customer or service provider.

**Format**: `tagContact INDEX t/TAG`

**Examples**:
* `tagContact 1 t/Customer`
* `tagContact 2 t/Service`

### 2. Trip Management

#### Add Trip: `addTrip`
Schedules a new trip with associated details.

**Format**: `addTrip n/NAME a/ACCOMMODATION i/ITINERARY d/DATE c/CUSTOMER_NAME... [nts/NOTE]`

**Examples**:
* `addTrip n/Paris 2025 a/Hotel Sunshine i/Visit Eiffel Tower; Eat baguette d/01/1/2025 c/Jane Doe c/John Doe nts/Customer prefers window seat`
* `addTrip n/Beach Vacation a/Beach Resort i/Relax by the beach; Snorkeling d/15/3/2024 c/Alice Smith nts/All-inclusive package`

**Parameter Requirements**:
* `NAME`: Any alphanumeric characters and spaces
* `ACCOMMODATION`: Any alphanumeric characters and spaces
* `ITINERARY`: Any text, can include multiple activities separated by semicolons
* `DATE`: Must be in DD/MM/YYYY format
* `CUSTOMER_NAME`: Any alphanumeric characters and spaces
* `NOTE`: Any text (optional)

#### Delete Trip: `deleteTrip`
Removes a trip from the system.

**Format**: `deleteTrip INDEX`

**Example**: `deleteTrip 1`

### 3. Notes Management

#### Add Note: `addNote`
Adds notes to customer profiles or trips.

**Format**: `addNote INDEX NOTE`

**Examples**:
* `addNote 1 Special dietary requirements`
* `addNote 2 Important meeting at 10 AM`


## Command Summary

| Command           | Format                                                             | Example                                                                                  |
|-------------------|--------------------------------------------------------------------|------------------------------------------------------------------------------------------|
| Add Contact       | `addContact n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]... [nts/NOTE]` | `addContact n/John Doe p/98765432 e/johnd@example.com a/123 Main Street t/customer nts/Preferred contact method is email` |
| Delete Contact    | `deleteContact INDEX`                                              | `deleteContact 1`                                                                        |
| Edit Contact      | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]... [nts/NOTE]` | `edit 1 p/87654321 e/john.doe@example.com` |
| Add Trip          | `addTrip n/NAME a/ACCOMMODATION i/ITINERARY d/DATE c/CUSTOMER_NAME... [nts/NOTE]` | `addTrip n/Paris 2025 a/Hotel Sunshine i/Visit Eiffel Tower d/01/1/2025 c/Jane Doe nts/Customer prefers window seat` |
| Delete Trip       | `deleteTrip INDEX`                                                 | `deleteTrip 1`                                                                           |
| Edit Trip         | `editTrip INDEX [n/NAME] [a/ACCOMMODATION] [i/ITINERARY] [d/DATE] [c/CUSTOMER_NAME]... [nts/NOTE]` | `editTrip 1 i/Visit Eiffel Tower; Visit Louvre` |

## Acknowledgements
* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

