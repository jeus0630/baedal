음식 API (`/food`)

- 음식 등록
    + HTTP Method: POST
    + API URL: `/food`
    + Request Body
      ```java
      {
          "name": "피자",
          "price": 9900,
          "categoryId": 1,
          "restaurantId": 1
      }
      ```
    + Status Code: 201
    + Response Body
      ```java
      {
          "id": 1,
          "name": "피자",
          "price": 9900,
          "categoryId": 1,
          "restaurantId": 1
      }
      ```

- 음식 조회
    + HTTP Method: GET
    + API URL: `/food`
    + Status Code: 200
    + Response Body
      ```java
      [ 
          {
              "id": 1,
              "name": "피자",
              "price": 9900,
              "categoryId": 1,
              "restaurantId": 1
          }
      ]
      ```

- 음식 업데이트
    + HTTP Method: PUT
    + API URL: `/food/:id`
    + Status Code: 200
    + Request Body
      ```java
      {
         "name": "피자",
         "price": 9900,
         "categoryId": 1,
         "restaurantId": 1
      }
      ```
    + Response Body
      ```java
      {
         "id": 1,
         "name": "피자",
         "price": 9900,
         "categoryId": 1,
         "restaurantId": 1
      }
      ```

- 음식 삭제
    + HTTP Method: DELETE
    + API URL: `/food/:id`
    + Status Code: 200
    + Response Body
      ```java
      {
          "id": 1
      }
      ```

- Table
    - Food

      | id  | name | price | categoryId | restaurantId |   
      |------|------|------| ----- |-----------|   
      | 1   | "피자" | 9900  | 1 | 1         | 

    - Restaurant

      | id  | name   | location       |   
      |--------|----------------|------|   
      | 1   | "구구피자" | "구구시 구구로 99-9" | 

    - Category

      | id  | name |    
      |------|----------------|   
      | 1   | "양식" | 
