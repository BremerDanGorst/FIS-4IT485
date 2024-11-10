Some practical implementation of most common Message Transformation Pattern

To test endpoint: 
curl --request POST \
  --url http://localhost:8080/envelopeWrapper \
  --header 'Content-Type: application/json' \
  --data '{
  "content": "Message provided by some service"
}
