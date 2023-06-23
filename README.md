# API Documentation

# Endpoints that don't require authentication

## Buy a Product
**Endpoint:** `(POST) /api/base/transaction`
**Request Body:**
```json
{
    "customerName":"Miles",
    "address":"Example Street",
    "product":{
        "id": 1,
        "name": "table Super Mahal enak tedan tdr 1000",
        "description": "Lorem Ipsum",
        "price": 37.6,
        "slug": "table_super_mahal_enak_tedan_tdr_1000",
        "picture": "ambasing.png"
    }
    
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
**Endpoint:** `(GET) /api/base/product/{slug}
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
**Endpoint:** 
**Request Body:**
**Response:**


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
```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJibnV5QGdtYWlsLmNvbSIsImlhdCI6MTY4NzQ5MjkxNiwiZXhwIjoxNjg3NTc5MzE2fQ.qj0NPMyTmlJn-L-6LTwShVOIOalQWvhGDFFos3YDwPc"
}
```

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

# Endpoints that require authentication

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
    "stock":200,
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