# consumer and the first contract

* show the test class, launch it (it will generate a contract file)
* show the contract file
* matchers (type vs value)
* publish the contract (version 1.0.0) 

# broker
* show the broker
* show the contract 
* * show the relationship graph
* * show versions and tags
* * explain relation between the consumer and provider (versioning + tags)
* best practices: git hash, branch, environment name, etc.

# provider and the first contract
* show an empty class for a pact test (just an initial stuff...)
* * comment the existing test methods
* launch the test
* * test fails. Explain why...
* explain `@State` - how it works, what it does -> how it "finds/maps" contract to the impl.
* show a sample controller class - explain that it will be tested (relation to the @State annotation)
* * comment the `@GetMapping("/users/{id}")` so that the API will not work!
* implement all tests for the contract
* launch tests
* publish results
* show the broker and its state
* make a change in order to BREAK the contract
* * launch tests
* * show results

# Consumer publishes a new contract
* add a new field to the contract (int age)
* publish a new contract WITH the new version number (1.1.0)

# Provider
* launch tests in the provider
* * show that it fails. Explain why we do not want it! (pending contracts...)
* * make the contract pending
* * launch test again -> they pass now...
* * show logs, information that the contract is in the `pending` state
* * publish results to the broker (failed results...)
* * fix the contract, build it again
* * show that tests pass now
* * publish results to the broker

# In adition, explain
* `consumerVersionSelector` explain it 
* how to have a test only for one consumer!
* how to have a test only for one version
* how to test events (CDC for Event-driven architecture)
