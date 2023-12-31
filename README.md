# API Documentation
- [UnAuthorized Endpoints](https://github.com/Clouza/Jumpstart-API#unauthorized-endpoints-without-jwt)
- [Authorized Endpoints](https://github.com/Clouza/Jumpstart-API#authorized-endpoints-with-jwt)

# UnAuthorized Endpoints (without JWT)
**Endpoint List**
- [Buy a Product](https://github.com/Clouza/Jumpstart-API#buy-a-product)
- [Get Single Transaction Data](https://github.com/Clouza/Jumpstart-API#get-single-transaction-data)
- [Get All Products](https://github.com/Clouza/Jumpstart-API#get-all-products)
- [Get Single Products](https://github.com/Clouza/Jumpstart-API#get-single-product)
- [Search Products](https://github.com/Clouza/Jumpstart-API#search-products)
- [Get Single User](https://github.com/Clouza/Jumpstart-API#get-single-user)
- [Update User Profile](https://github.com/Clouza/Jumpstart-API#update-user-profile)
- [Register](https://github.com/Clouza/Jumpstart-API#update-user-profile)
- [Login](https://github.com/Clouza/Jumpstart-API#login)

## Buy a Product
**Endpoint:** `(POST) /api/base/transaction`
**Request Body:**
```json
{
    "customerName":"Miles",
    "address":"Example Street",
    "retailRegionProductId":1
    
}
```
**Response:**
`Successfully ordered the product`

## Get Single Transaction Data
**Endpoint:** `(GET) /api/base/transaction/{id}`
**Response:**
```json
{
    "id": 1,
    "customerName": "Miles",
    "address": "Example Street",
    "status": "PENDING",
    "date": "23/06/2023 14:05:21",
    "products": {
        "id": 1,
        "name": "table Super Mahal enak tedan tdr 1000",
        "description": "Lorem Ipsum",
        "price": 37.6,
        "slug": "table_super_mahal_enak_tedan_tdr_1000",
        "picture": "ambasing.png"
    }
}
```

## Get All Products
**Endpoint:** `(GET) /api/base/product`
**Response:**
```json
[
    {
        "id": 1,
        "name": "table Super Mahal enak tedan tdr 1000",
        "description": "Lorem Ipsum",
        "price": 37.6,
        "slug": "table_super_mahal_enak_tedan_tdr_1000",
        "picture": "ambasing.png"
    },
    {
        "id": 2,
        "name": "chair Super Mahal enak tedan tdr 1000",
        "description": "Lorem Ipsum",
        "price": 37.6,
        "slug": "chair_super_mahal_enak_tedan_tdr_1000",
        "picture": "ambasing.png"
    }
]
```

## Get Single Product
**Endpoint:** `(GET) /api/base/product/{slug}`
**Response:**
```json
{
    "id": 2,
    "name": "chair Super Mahal enak tedan tdr 1000",
    "description": "Lorem Ipsum",
    "price": 37.6,
    "slug": "chair_super_mahal_enak_tedan_tdr_1000",
    "picture": "ambasing.png"
}
```

## Search Products
**Endpoint:** `(GET) /api/base/products`
**Path Parameter:** query
**Response:**
```json
[
    {
        "id": 1,
        "name": "table Super Mahal enak tedan tdr 1000",
        "description": "Lorem Ipsum",
        "price": 37.6,
        "slug": "table_super_mahal_enak_tedan_tdr_1000",
        "picture": "ambasing.png"
    }
]
```

## Get Single User
**Endpoint:** `(GET) /api/base/user/{token}`
**Response:**
```json
{
    "id": 1,
    "email": "bnuy@gmail.com",
    "password": "$2a$10$3nANdRma5w.yMw57mihIau9XeFHa/EXrAn2U2y.LWz1JPEC7XJv9O",
    "userDetail": {
        "firstname": "Bnuy",
        "lastname": "Slayer",
        "address": "Haven't Decided",
        "age": 0,
        "gender": "Haven't Decided",
        "id": 1
    },
    "role": "ADMIN",
    "enabled": true,
    "accountNonLocked": true,
    "authorities": [
        {
            "authority": "ADMIN"
        }
    ],
    "username": "bnuy@gmail.com",
    "credentialsNonExpired": true,
    "accountNonExpired": true
}
```

## Update User Profile
**Endpoint:** `(POST) /api/base/user/profile` 
**Request Body:**
```json
{
    "id": "1",	
    "email": "example@mail.com",	
    "password": "secretPasswordQ@[~!",
    "firstname": "Example",
    "lastname": "Example",
    "age": "78",
    "address": "Bali, Indonesia",
    "gender": "Male"
}
```
**Response:**
```json
{
    "id": "1",	
    "email": "example@mail.com",	
    "password": "secretPasswordQ@[~!",
    "firstname": "Example",
    "lastname": "Example",
    "age": "78",
    "address": "Bali, Indonesia",
    "gender": "Male"
}
```

## Register
**Endpoint:** `(POST) /api/base/auth/register`
**Request Body:**
```json
{
    "email":"bnuy@gmail.com",
    "password": "123",
    "firstname": "Bnuy",
    "lastname": "Slayer"
}
```
**Response:**
`Successfully Register`

## Login
**Endpoint:** `(POST) /api/base/auth/authenticate`
**Request Body:**
```json
{
    "email":"bnuy@gmail.com",
    "password": "123"
}
```
**Response:**
```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJibnV5QGdtYWlsLmNvbSIsImlhdCI6MTY4NzQ5MjkxNiwiZXhwIjoxNjg3NTc5MzE2fQ.qj0NPMyTmlJn-L-6LTwShVOIOalQWvhGDFFos3YDwPc"
}
```

# Authorized Endpoints (with JWT)
**Endpoint List**
- [Add a New Retail Region](https://github.com/Clouza/Jumpstart-API#add-a-new-retail-region)
- [Add Product to Retail Region](https://github.com/Clouza/Jumpstart-API#add-product-to-retail-region)
- [Add a Product](https://github.com/Clouza/Jumpstart-API#add-a-product)
- [Get All Transactions](https://github.com/Clouza/Jumpstart-API#get-all-transactions)
- [Get All Products based on their Retail Region](https://github.com/Clouza/Jumpstart-API#get-all-products-based-on-their-retail-region)

## Add a New Retail Region
**Endpoint:** `(POST) /api/admin/retail-regions`
**Request Body:**
```json
{
    "name":"Retail Regions 1",
    "location": "KK Street"
}
```
**Response:**
`Successfully Create a Retail Region`

## Add Product to Retail Region
**Endpoint:** `(POST) /api/admin/retail-regions/product
**Request Body:**
```json
{
    "retailId":1,
	"productId": 1,
	"stock": 20
}
```
**Response:**
`Successfully add product to retail region`

## Add a Product
**Endpoint:** `(POST) /api/admin/product`
**Request Body:**
```json
{
    "name":"chair Super Mahal enak tedan tdr 1000",
    "picture":"ambasing.png",
    "description": "Lorem Ipsum",
    "price":37.6
}
```
**Response:**
`Successfully added new product`

## Get All Transactions
**Endpoint:** `(GET) /api/admin/transaction`
**Response:**
```json
[
    {
        "id": 1,
        "customerName": "Miles",
        "address": "spi",
        "status": "PENDING",
        "date": "23/06/2023 14:05:21",
        "products": {
            "id": 1,
            "name": "table Super Mahal enak tedan tdr 1000",
            "description": "Lorem Ipsum",
            "price": 37.6,
            "slug": "table_super_mahal_enak_tedan_tdr_1000",
            "picture": "ambasing.png"
        }
    }
]
```

## Get All Products based on their Retail Region
**Endpoint:** `(GET) /api/admin/retail-regions/{retailId}/products`
**Response:**
```json
[
    {
        "id": 1,
        "retailRegion": {
            "id": 1,
            "name": "Retail Regions 1",
            "location": "KK Street"
        },
        "product": {
            "id": 2,
            "name": "chair Super Mahal enak tedan tdr 1000",
            "description": "Lorem Ipsum",
            "price": 37.6,
            "slug": "chair_super_mahal_enak_tedan_tdr_1000",
            "picture": "ambasing.png"
        },
        "stock": 0
    },
    {
        "id": 2,
        "retailRegion": {
            "id": 1,
            "name": "Retail Regions 1",
            "location": "KK Street"
        },
        "product": {
            "id": 1,
            "name": "table Super Mahal enak tedan tdr 1000",
            "description": "Lorem Ipsum",
            "price": 37.6,
            "slug": "table_super_mahal_enak_tedan_tdr_1000",
            "picture": "ambasing.png"
        },
        "stock": 0
    }
]
```
## Approve Transaction
**Endpoint:** `(GET) /api/admin/transaction/approve/{transactionId}`
**Response:**
`Successfully approve a product`

## Update a Product
**Endpoint:** `(PUT) /api/admin/product`
**Request Body:**
```json
{
  "id": 1,
  "name": "Wood Table",
  "description": "Lorem Ipsum",
  "price": 49.9,
  "picture": "wc.png"
}
```
**Response:**
`Successfully update product`

## Delete a Product
**Endpoint:** `(PUT) /api/admin/product/{id}`
**Response:**
`Successfully delete product`