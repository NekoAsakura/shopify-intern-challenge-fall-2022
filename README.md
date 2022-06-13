<h1 align="center">Shop Lite</h1>
<h3 align="center">An inventory tracking web application</h3>
<p align="center">
<a title="GitHub Watchers" target="_blank" href="https://github.com/NekoAsakura/shopify-intern-challenge-fall-2022/watchers"><img src="https://img.shields.io/github/watchers/NekoAsakura/shopify-intern-challenge-fall-2022.svg?label=Watchers&style=social"></a>  
<a title="GitHub Stars" target="_blank" href="https://github.com/NekoAsakura/shopify-intern-challenge-fall-2022/stargazers"><img src="https://img.shields.io/github/stars/NekoAsakura/shopify-intern-challenge-fall-2022.svg?label=Stars&style=social"></a>  
<a title="GitHub Forks" target="_blank" href="https://github.com/NekoAsakura/shopify-intern-challenge-fall-2022/network/members"><img src="https://img.shields.io/github/forks/NekoAsakura/shopify-intern-challenge-fall-2022.svg?label=Forks&style=social"></a>  
<a title="Author GitHub Followers" target="_blank" href="https://github.com/NekoAsakura"><img src="https://img.shields.io/github/followers/NekoAsakura.svg?label=Followers&style=social"></a>
</p>

## Introduction
[Shop Lite](https://github.com/NekoAsakura/shopify-intern-challenge-fall-2022) is an inventory tracking web application.   
Click [here](https://replit.com/@NekoAsakura/Shop-Lite?v=1) to run the application on replit.  
*Notes: Open website in new tab by clicking on "Open website". Otherwise redirect function may not work as expected.*

## Technology stack

[Spring Boot](https://projects.spring.io/spring-boot/) for application configuration.  
[Maven](https://maven.apache.org/) configuration for building, testing and running the application.   
[Spring Data JPA](https://projects.spring.io/spring-data-jpa/) for database access.  
[Thymeleaf](https://www.thymeleaf.org/) for HTML templating.  
[Spring MVC REST](https://spring.io/guides/gs/rest-service/) (Not implemented)

## Features

- Basic CRUD Functionality.
- Ability to create “shipments” and assign inventory to the shipment, and adjust inventory appropriately.

## How to use

### run the server
**Suggested:**
1. Open repl in [replit](https://repl.it/@NekoAsakura/Shop-Lite?v=1).
2. Click "Run".
3. Click "Open website" to open it in a new tab.

**Run in local machine:**
1. Clone the repository.  
```git clone git@github.com:NekoAsakura/shopify-intern-challenge-fall-2022.git```
2. Open terminal (bash/zsh) and navigate to the directory.
3. Run the following command:  
```cd shoplite; ./mvnw spring-boot:run```
4. Visit the website in [localhost:8080](localhost:8080).