Запуск:
1) сбилдить образ: docker build . -t test-dc
2) запустить: docker run -p 127.0.0.1:8080:8080 test-dc:latest    
 
Протестировать API можно через swagger-ui  http://localhost:8080/swagger-ui.html

Примеры вызова из Postman находятся в PostmanCollection.postman_collection.json 

