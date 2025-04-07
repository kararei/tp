---
  layout: default.md
  title: "Zyon Wee's Project Portfolio Page"
---

### Project: TravelHub

**PPP: TravelHub Project Contributions - Zyon Wee**

**Overview:**

TravelHub is a command-line application designed to efficiently manage trip and contact information, streamlining travel planning. It allows users to add, edit, delete, and find trip and contact details, ensuring organized and accessible travel data.

**Summary of Contributions:**

* **Code Contributed:**
    * Link to tP Code Dashboard:
      [link](https://nus-cs2103-ay2425s2.github.io/tp-dashboard/?search=zyonwee&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2025-02-21&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **Enhancements Implemented:**
    * **Delete Trip and Delete Contact Commands:** Implemented the functionality to delete trips and contacts, including robust error handling and user feedback. This involved significant logic modifications and test case implementations to ensure reliability.
    * **Date Validation and Logging:** Enhanced the `TripDate` class by adding detailed logging for date validation, improving debugging and maintainability. Implemented assertions and strengthened validation logic to prevent erroneous data entry.
    * **Phone Number Length Validation:** Added validation and message updates for phone number length, improving data integrity and user experience.
    * **Refactoring and Renaming:** Performed extensive refactoring to improve code clarity and maintainability, including renaming classes, methods, and variables for consistency (e.g., renaming "Address Book" to "TravelHub", "Delete" to "Delete Contact").
    * **Checkstyle Enforcement:** Consistently enforced Checkstyle rules to maintain code quality and consistency across the project.
    * **Bug Fixes:** Resolved multiple bugs related to command functionality and user interface elements.
* **Contributions to the UG:**
    * Updated the "Delete Trip" and "Delete Contact" sections of the User Guide, providing clear instructions and examples for users.
* **Contributions to the DG:**
    * Added a "Glossary" section to the Developer Guide, defining key terms for better understanding of the project's architecture.
  * Added Use Cases
    * Use Case: See Usage Instructions
    * Use Case: Add a Contact
    * Use Case: Add a Trip
    * Use Case: Delete a Contact
    * Use Case: Delete a Trip
    * Use Case: Mark a Trip as Ongoing
    * Use Case: Mark a Trip as Completed
    * Use Case: Search for Contacts and Trips
    * Use Case: Export Customer and Service Information
    * Use Case: Add Notes to Customer Profiles or Trips
    * Use Case: Restrict Adding Service Contacts as Trip Members
    * Use Case: Restrict Adding Customer Contacts as Trip Location Businesses
    * Use Case: Sort Contacts by Name
  * Added UML diagrams for delete functionality.
* **Contributions to Team-Based Tasks:**
    * Contributed to the team by merging pull requests and resolving merge conflicts.
    * Updated code coverage.
* **Review/Mentoring Contributions:**
    * Reviewed pull requests, providing constructive feedback to team members.
* **Contributions Beyond the Project Team:**
    * Contributed to the "About Us" section, enhancing team presentation.

**OPTIONAL Contributions to the Developer Guide (Extracts):**

* **Glossary Section:**
    * Included definitions for key terms such as "Trip," "Contact," and "Command" to ensure consistent understanding among developers.
* **UML Diagrams:**
    * Added UML activity and sequence diagrams for the delete contact and delete trip commands.
    * Removed unnecessary termination nodes from UML diagrams.
* **Use Case Diagrams:**
    * Added use case diagrams for the various functions of the application.

**OPTIONAL Contributions to the User Guide (Extracts):**

* **Delete Trip/Contact Section:**
    * Provided detailed steps and examples on how to use the `delete trip` and `delete contact` commands, including error handling scenarios.
    * Example:
        * `delete trip 1` : deletes the first trip in the list.
        * `delete contact 2`: deletes the second contact in the list.

**Explanation of Effort:**

* The implementation of the `delete trip` and `delete contact` commands required careful consideration of data structures, command logic, and user feedback, ensuring that data integrity was maintained.
* Enhancing date validation and adding logging significantly improved the robustness of the `TripDate` class, preventing potential errors and simplifying debugging.
* Refactoring and renaming efforts improved code clarity and consistency, contributing to the overall maintainability of the project.
* Creating clean and effective UML diagrams required a deep understanding of the code base.
* The creation of the glossary in the Developer Guide was a important contribution to the overall understanding of the project.
* The various checkstyle enforcement commits, while small individually, show a dedication to code quality across the entire project.
