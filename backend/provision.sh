# Add owner
curl \
  -H "Content-Type: application/json" \
  --data '{"firstName": "George", "lastName": "Franklin", "address": "110 W. Liberty St.", "city": "Madison", "telephone": "6085551023"}' \
  http://localhost:8080/owners

# Add pet
curl \
  -H "Content-Type: application/json" \
  --data '{"name": "Coco", "birthDate": "2010-09-07", "type": "cat", "ownerId": 1}' \
  http://localhost:8080/pets

# Add visit
curl \
  --header "Content-Type: application/json" \
  --data '{"date": "2023-04-18", "description": "Foo", "petId": 1}' \
  http://localhost:8080/visits