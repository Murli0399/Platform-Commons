# Student Management System

A web application for managing students and courses.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)

## Introduction

The Student Management System is designed to ease the process of managing students and courses in a school or educational institution. It provides features for administrators to admit students, upload course details, assign courses to students, search for students by name, and more. Students can update their profiles, search for assigned courses, and leave courses.

## Features

- Admin Operations:
  - Admit students with detailed information.
  - Upload course details (name, description, type, duration, topics).
  - Assign courses to students.
  - Search for students by name.
  - Get students assigned to a particular course.
  - Admin authentication using Spring Security.

- Student Operations:
  - Update profile details (email, mobile number, parents' name, address).
  - Search for assigned topics/courses.
  - Leave a course.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/student-management-system.git
   cd student-management-system
