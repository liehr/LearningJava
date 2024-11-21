# Week 1 of Learning Java

| Topic                       | Difficulty | Status     | Goals                                                                                    |
|-----------------------------|------------|------------|------------------------------------------------------------------------------------------|
| Calculator                  | 🟢 Easy    | ✅ Done     | Learn Java syntax, basic operators, control structures, input/output handling            |
| To-Do List Application      | 🟢 Easy    | ✅ Done     | Practice `ArrayList`, OOP basics (classes and objects), menu-driven program structure    |
| Bank Account Simulator      | 🟡 Medium  | ✅ Done     | Understand classes, methods, object-oriented principles, and basic error handling        |
| Contact Book with File I/O  | 🟡 Medium  | ⬜ Not Done | Work with file operations, `HashMap`, and exception handling                             |
| Mini E-Commerce Console App | 🔴 Hard    | ⬜ Not Done | Combine OOP concepts, `ArrayList` and `HashMap`, user interactions, and simple inventory |

## Week 1 Learning Goals
- Familiarize with basic Java syntax, operators, and control structures
- Build foundational understanding of Java OOP concepts
- Develop and test two small projects (Calculator, To-Do List) to apply learned concepts

## Daily Progress
- **Day 1:** Learned what `Test Driven Development` is! Tried to incorporate it in developing the Calculator Application.
- **Day 2:** Holy shit! `TDD` takes a lot of time! But today was a huge progress day! CRUD for Todos is done, the calculator is finished and I learned how to test Models.
- **Day 3:** Unfortunately was in hospital for 3 days!
- **Day 4:** Unfortunately was in hospital for 3 days!
- **Day 5:** Unfortunately was in hospital for 3 days!
- **Day 6:** Learned what immutable models are and implemented it for the `AccontHolder.java`!
- **Day 7:** Implemented the BankAccount model as well as the Transaction model.

## Lessons Learned
- **Learned to use `TDD` even for basic and easy applications like the calculator. It's not convenient in the first place but
thinking more about what the functionality of methods is, even before implementing them is amazing!**
- **Learned how to fully test models. I had to ask ChatGPT how it's possible to modify Objects without modifying the internal state. `Copy Constructors` and `Mapping` when retrieving data.**
- **Learned the Advantages and Disadvantages of `immutable models`! It comes at a cost, but hybrid options like the `Builder pattern` are good approaches.**

## Challenges and Solutions
- **Issue:** When retrieving data with a getter, modifying the data without calling an update function. This changes the internal state of the object.
    - **Solution:** Using `Copy Constructors` and `Mapping` the Objects with it, so the internal state won't change.

## Project Summaries
- **Calculator:** Overall very easy. Learned to work with double instead of int. Had to learn the hard way, that using Math Libs isn't bad when extreme precision isn't necessary!
- **To-Do List Application:** Learned to fully test models and small applications that use the model.
- **Bank Account Simulator:** Learned what mutable and immutable fundamentally is. Loved to look at functional mutation approaches. Added a class called `Transactio` that symbolizes a real transaction between two bank accounts!

## Next Steps
- **ContactBook Application with File I/O. I have no idea how this works using Java. But using `JSON` for storing and retrieving data would be a nifty idea.
