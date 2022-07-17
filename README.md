# anneaDemoTask

The project is about querying a large .csv file and expose an api for that.

## My approach

- I have used Java, spring boot & mySql for the project
- As the data source is provided in .xlsx format, so I processed the workbook by using [Apache POI](https://poi.apache.org/). For importing data from .xlsx to database
I have exposed an post api ---> /importExcelData
```sh
curl --location --request POST 'http://localhost:8082/importExcelData' \
--form 'file=@"/F:/Downloads/example_indicator (1) - Copy.xlsx"'
```
- For getting data back from db, I have developed an api called ---> /search which takes "search" as a parameter. And I have made this parameter dynamic.
- To use dynamic query parameters I have used [JPA Specification](https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/)
- I have used paging technique to handle large data page by page

## How to query the get api
we can query the data by any params with pageNumber & pageSize params. Here is a sample call :
```sh
http://localhost:8082/search?search=timeStamp>"2021-12-30 00:00:00",turbineId:"40",pageNumber:"10",pageSize:"2"
```
sample response:
```sh
{
    "numberOfElements": 2,
    "pageNumber": 10,
    "totalElements": 193,
    "content": [
        {
            "serialNo": 99994,
            "timeStamp": 1640877600000,
            "indicator": 14.071224955599263,
            "turbineId": 40,
            "variable": 1,
            "dateTime": "2021-12-30 21:20:00"
        },
        {
            "serialNo": 99995,
            "timeStamp": 1640887200000,
            "indicator": 14.071011111628437,
            "turbineId": 40,
            "variable": 1,
            "dateTime": "2021-12-31 00:00:00"
        }
    ],
    "pageSize": 2,
    "totalPages": 97
}
```
- So, dynamically query able api with paging support will make handling the large data easier
- The project is structured layer wise so that code remains self explainary 
