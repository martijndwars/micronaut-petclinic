### Owners

# List owners
curl \
  -v \
  http://localhost:8080/owners

# Add owner
curl \
  -v \
  -X POST \
  -H "Content-Type: application/json" \
  --data '{"firstName": "George", "lastName": "Franklin", "address": "110 W. Liberty St.", "city": "Madison", "telephone": "6085551023"}' \
  http://localhost:8080/owners

# Delete pet
curl \
  -v \
  -X DELETE \
  -H "Content-Type: application/json" \
  http://localhost:8080/pets/1

### Pets

# List pets
curl \
  -v \
  http://localhost:8080/pets

# Add pet
curl \
  -v \
  -X POST \
  -H "Content-Type: application/json" \
  --data '{"name": "Coco", "birthDate": "2010-09-07", "ownerId": 1}' \
  http://localhost:8080/pets

# Delete pet
curl \
  -v \
  -X DELETE \
  -H "Content-Type: application/json" \
  http://localhost:8080/pets/1

### Vets

# List vets
curl http://localhost:8080/vets

# Add vet
curl \
  --request POST \
  --header "Content-Type: application/json" \
  --data '{"name": "Dr. Crock"}' \
  http://localhost:8080/vets

# Delete vet
curl \
  --request DELETE \
  --header "Content-Type: application/json" \
  http://localhost:8080/vets/1

### Visits

# List visits
curl http://localhost:8080/visits

# Add visit
curl \
  --request POST \
  --header "Content-Type: application/json" \
  --data '{"date": "2023-04-18", "description": "Foo", "petId": 1}' \
  http://localhost:8080/visits
