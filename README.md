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

**Format**: `addContact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]...`

**Examples**:
* `addContact n/John Doe p/98765432 e/johndoe@example.com a/123 Main Street t/Customer`
* `addContact n/Beach Resort p/12345678 e/resort@example.com a/456 Beach Road t/Service`

**Parameter Requirements**:
* `NAME`: Alphabetic characters, spaces, and hyphens allowed
* `PHONE_NUMBER`: Must be 8 digits
* `EMAIL`: Must follow standard email format (e.g., user@domain.com)
* `ADDRESS`: Any alphanumeric characters, spaces, and common punctuation marks
* `TAG`: Must be either Customer or Service (optional)

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

**Format**: `addTrip d/DATE c/CUSTOMER_INDEX a/ACCOMMODATION i/ITINERARY`

**Examples**:
* `addTrip d/2023-12-25 c/1 a/Hotel Sunshine i/Visit Eiffel Tower`
* `addTrip d/2024-01-01 c/2 a/Beach Resort i/Relax by the beach`

**Parameter Requirements**:
* `DATE`: Must be in YYYY-MM-DD format
* `CUSTOMER_INDEX`: Must be a positive integer representing the customer's position in the list
* `ACCOMMODATION`: Any alphanumeric characters and spaces
* `ITINERARY`: Any alphanumeric characters and spaces

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
| Add Contact       | `addContact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]...`    | `addContact n/John Doe p/98765432 e/johndoe@example.com a/123 Main Street t/Customer`    |
| Delete Contact    | `deleteContact INDEX`                                              | `deleteContact 1`                                                                        |
| Tag Contact       | `tagContact INDEX t/TAG`                                           | `tagContact 1 t/Customer`                                                                |
| Add Trip          | `addTrip d/DATE c/CUSTOMER_INDEX a/ACCOMMODATION i/ITINERARY`      | `addTrip d/2023-12-25 c/1 a/Hotel Sunshine i/Visit Eiffel Tower`                         |
| Delete Trip       | `deleteTrip INDEX`                                                 | `deleteTrip 1`                                                                           |
| Add Note          | `addNote INDEX NOTE`                                               | `addNote 1 Special dietary requirements`                                                 |

## Acknowledgements
* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

