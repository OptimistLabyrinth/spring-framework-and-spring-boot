# GET plus (request param)

request: a + b

response: { result: (a + b) }

# POST plus (request body)

request: a + b

response: { result: (a + b) }

# GET minus (request param)

request: a - b

response: { result: (a - b) }

# POST minus (request body)

request: a - b

response: { result: (a - b) }

# GET multiply (request param)

request: a * b

response: { result: (a * b) }

# POST multiply (request body)

request: a * b

response: { result: (a * b) }

# GET divide (request param)

request: a / b

response: { result: (a / b) }

# POST divide (request body)

request: a / b

response: { result: (a / b) }

# GET modulus (request param)

request: a % b

response: { result: (a % b) }

# POST modulus (request body)

request: a % b

response: { result: (a % b) }

# POST expression

support multiple operand
support parenthesis

request: 8 / ((1 + 3) * (5 - 3)) + 14 - (2 * 4)

response: { result: 7 }
