# HTTP Server Spec

## Background

The HTTP server project takes you through building a functional HTTP server from bare sockets up. It has a long and proud tradition at 8th Light of being challenging, eye-opening, and extremely rewarding.

We have created a suite of acceptance tests in an effort to guide you through the process of building the server. It can be difficult to test some of the features of the server directly, so these tests will be asserting that you can serve data in a variety of ways:

1. Hardcoded Responses
1. File Server
1. To-Do list Application

#### 01 Getting Started

In this first section, you will build out some of the basic infrastructure of your server including simple routes, requests, and responses. It might be ok to return hardcoded responses to get started with building out the HTTP server, but try to create a way to specify customizable routes with any specified verb. The IETF RFC documentation might be a good reference as you work in this section.

#### 02 Structured Data

Now that we have built out a basic server, we can ensure that it returns data with different formats. These tests ensure that your server can return data in text, HTML, JSON, and XML formats. You might have to consider some of the specific headers associated with requests and responses.

#### 03 File Server

You might have been serving in-memory or hard-coded responses to your requests up until now. It is time to serve data that is stored on a disk. The `web/` directory contains a small collection of files that you will serve via your HTTP server. Copy that directory to the location on disk where you want the root of your project to be. The features in this section will serve each of those files.

#### 04 To-Do List

Web servers often sit in front of application servers in order to filter, pre-process, and distribute requests. In this final set of features, you will connect a separate to-do list application to your HTTP server. By the end of this step, you have built a reverse proxy server from sockets up! Pretty neat!

Start off by building a simple to-do list JSON API using a framework like Rails or Django. Each task in the to-do list should have three attributes:

1. A numeric ID (which is often generated by the database as a primary key).
1. A description of the task (string)
1. An indicator of whether the task is complete or not (boolean)

The app should give a user the ability to perform CRUD operations on tasks (you can choose how the data is persisted):

1. Create a new task with a description and an indicator.
1. Retrieve the ID, description, and indicator of a single task.
1. Retrieve the IDs, descriptions, and indicators of all of the tasks (i.e. an index).
1. Update the description or indicator of a specified task.
1. Delete a specified task.

Then, instead of making requests through the application server directly route the requests through your HTTP server. The feature specs are there to test if you can successfully make requests through the proxy server.

## Installation

This project uses Ruby and the Spinach test runner. You can install Ruby using any widely used Ruby package manager. Next, install [Bundler](https://bundler.io/). Finally, run the following command to pull down and install the required dependencies for the project:

```
bundle install
```

Note: if you're using system ruby, you may need to configure Bundler to use a local writable path before installing:

```
bundle config set --local path vendor/bundle
bundle install
```

## Running the Tests

Start your HTTP server on port 8080 (this will probably be done in a separate tab or terminal window). Note that this will require different commands depending on your tech stack, your project configuration, and how your project is built. Mentors and apprentices should discuss these details to make sure everyone is on the same page on how to build the project.

Once your server is running, you can run the acceptance test suite with:

```
rake test
```

You can also run the tests from a specific section of the features:

```
rake test:f1 # Run all of the tests in 01_getting_started
rake test:f2 # Run all of the tests in 02_structured_data
rake test:f3 # Run all of the tests in 03_file_server
rake test:f4 # Run all of the tests in 04_todo_list
```

The step definitions are found in `features/steps/**/*.rb`. Step definitions that are repeated between tests are located in `features/steps/shared.rb`, so be sure to look there if you are unable to find the definition you are looking for!

## Sample Server

In order to help you diagnose any problems that you might be having with the HTTP server, we have included a Sinatra server that passes all of the acceptance tests in `01_getting_started`, `02_structured_data`, and `03_file_server`. You can run the Sinatra server in a different terminal window and see it pass the specs:

```
rake server
rake test
```

## Continuous Integration

Ideally, you want to run the unit test suite for your HTTP server as well as the corresponding acceptance tests that are currently passing when you set up CI. You do not want to run the entire acceptance test suite in CI because that would lead to false failures and potential problems downstream. For example, your CI system might be connected to an automated deploy - false failures will prevent any sort of deployment from happening, even though you are iteratively building up your HTTP server.

How might we run a subset of the acceptance test suite in CI? There are lots of ways to do this, and you can customize your solution to your liking:

- Leverage [Git Submodules](https://git-scm.com/book/en/v2/Git-Tools-Submodules) to link the acceptance test suite to your HTTP server code.
- Leverage [Git Subtree](https://www.atlassian.com/git/tutorials/git-subtree) to link the acceptance test suite to your HTTP server code.
- Create and update a script that runs all of your unit tests as well as a subset of the acceptance tests based on the tags mentioned above.
- Leverage multi-step builds within your CI system to run unit and acceptance tests separately.

## FAQ

#### Am I allowed to change the acceptance tests as I work on a feature?

As much as possible don't change the acceptance tests themselves. Instead, change your code to make it pass the test suite. However, it might be useful to dive into the tests themselves and get a sense of how they are making assertions.

#### Do I need to finish every section in order to call this project "done"?

No! Remember that the goal of the projects during the apprenticeship is to efficiently cover the topics on the matrix. Your mentor-apprentice team can decide how many sections ought to be completed before moving onto the next task. Our recommendation is to cover the sections in order since they build on each other.

#### Can I use third party libraries when working on this project?

The answer to this question depends on the mentor-apprentice team. If you agree as a group that a few third party libraries are appropriate, go for it! If you want to roll out everything by hand, that is fine too! Our recommendation is to use as few external libraries as possible.

#### Should I leverage a templating library when building out features?

A templating library allows you to dynamically generate HTML in a structured way. Again, the answer to this question depends on the mentor-apprentice team. Many teams like to introduce templating libraries in the HTTP server project since they are so common in client work. If you are experiencing time-constraints when implementing certain features, a templating library might help abstract away many low-level details that get in the way of delivering useful work.

#### I found an issue with this repo. What should I do?

You can email `workshops@8thlight.com` with the details of the issue or just submit a PR. Thanks for your input!
