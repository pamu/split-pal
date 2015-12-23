[![Build Status](https://travis-ci.org/pamu/split-pal.svg?branch=master)](https://travis-ci.org/pamu/split-pal)

# Split-Pal
Split wise for Sane people in Scala Android -- By Pamu and Sai

thanks to [47deg] (http://47deg.com) [some of the code is taken from their open source projects on github]

## Rest end points

### Get User Details

Get user details using 10 digit phone number and country phone code

POST http://somedomain.com/v1/user

accepts application/json

request header: auth header

request body:

```
    {
        "api_version_number": 1,
        "api_version_name": "codefreak",
        "country_code": "+91",
        "phone_number":  1234567891
    }
```

response:

on success

```
    {
        "api_version_number": 1,
        "api_version_name": "codefreak",
        "error_code": 0,
        "payload": {
            "user_name": "something",
             "profile_pic": "http://somelink.com/user_name",
             "phone_number": 1234567890,
             "country_code": "+91",
             "registered_on": "<timestamp>"
        }
    }
```

on failure

```
    {
        "api_version_number": 1,
        "api_version_name": "codefreak",
        "error_code": 1,
        "payload": {
            "message": "failed for some reason"
         }
    }
```

note: error_code is the application specific code to communicate validation failure and other failures.
this has to be used only when http codes cannot express the failure use case. It is recommended to use 
http status codes as much as possible.