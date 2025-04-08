# Million Products Search Service

This project implements a scalable and reactive product search microservice designed to handle a large catalog of millions of products. It leverages the power of Kotlin, Spring Boot, Spring Cloud, and Reactive Elasticsearch to provide efficient product search, autocomplete suggestions, and faceted navigation.

## Featuress

* **Product Name Search:** Allows users to search for products based on keywords in their names.
* **Autocomplete:** Provides fast and relevant product name suggestions as users type, powered by Elasticsearch's Completion Suggester.
* **Faceted Search:** Enables users to refine their search results using facets based on:
    * **Category:** Filter products by their category.
    * **Rating:** Filter products by predefined rating ranges.
    * **Price:** Filter products by predefined price ranges.
    * **Bestseller:** Filter products to show only best-selling items.
* **Data Loading:** Includes a service to load product data from CSV files (`products.csv` and `categories.csv`) into the Elasticsearch index.
* **Reactive Architecture:** Built using Spring WebFlux and Reactive Elasticsearch for non-blocking and efficient I/O operations.
* **Robust Testing:** Comprehensive JUnit tests to ensure the reliability of each component.

## Technologies Used

* **Kotlin:** Primary programming language.
* **Spring Boot:** Framework for building the microservice.
* **Spring Cloud:** Provides tools for building distributed systems.
* **Reactive Elasticsearch:** Asynchronous and non-blocking client for interacting with Elasticsearch.
* **JUnit:** Framework for unit testing.
* **Gradle:** Build automation tool.

## Getting Started

### Prerequisites

* **Java Development Kit (JDK):** Ensure you have a compatible JDK installed (e.g., JDK 17 or later).
* **Gradle:** The project uses Gradle for building.
* **Elasticsearch:** You need a running Elasticsearch instance. The default configuration in `application.properties` assumes it's running on `localhost:9200`.

### Setup and Running Locally

1.  **Clone the Repository:**
    ```bash
    git clone <repository_url>
    cd million-product-search
    cd product-suggestions-service
    ```

2.  **Configure Elasticsearch Connection:**
    * Review and adjust the Elasticsearch connection details in `src/main/resources/application.properties` or `application.yml` if your Elasticsearch instance is running on a different host or port.

3.  **Prepare CSV Data:**
    * Ensure you have `products.csv` (and optionally `categories.csv`) files in the `src/main/resources` directory with the following structure (at least):
        ```csv
        id,name,category,rating,price,isBestseller
        1,Awesome T-Shirt,Apparel,4.5,25.99,true
        2,Cool Coffee Mug,Home Goods,4.2,12.50,false
        ...
        ```
        The headers are important for the data loading service.

4.  **Build the Application:**
    ```bash
    ./gradlew clean build
    ```

5.  **Run the Application:**
    ```bash
    java -jar build/libs/product-suggestions-service-0.0.1-SNAPSHOT.jar
    ```
    (The exact JAR file name might vary based on the version).

### API Endpoints

* **`GET /suggestions?q={query}`**:
    * Performs full-text search on product names and returns matching products along with facets.
    * Example: `/suggestions?q=coffee`
    * Response:
        ```json
        {
          "results": [
            // Array of ProductSuggestion objects
          ],
          "facets": {
            "categories": {
              "buckets": [
                // Category facet buckets
              ]
            },
            "ratings": {
              "buckets": [
                // Rating range facet buckets
              ]
            },
            "prices": {
              "buckets": [
                // Price range facet buckets
              ]
            },
            "bestsellers": {
              "buckets": [
                // Bestseller facet buckets
              ]
            }
          }
        }
        ```

* **`GET /autocomplete?prefix={prefix}`**:
    * Retrieves product name suggestions based on the provided prefix.
    * Example: `/autocomplete?prefix=coff`
    * Response:
        ```json
        [
          "Cool Coffee Mug",
          "Dark Roast Coffee"
        ]
        ```

## Testing

The project includes comprehensive JUnit tests. To run the tests:

```bash
./gradlew test