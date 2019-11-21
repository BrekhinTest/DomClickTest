Запуск:
1) собрать арттефакт: mvn clean install
2) сбилдить образ: docker build . -t test-dc
3) запустить: docker run -p 127.0.0.1:8080:8080 test-dc    
 
Протестировать API можно через swagger-ui  http://localhost:8080/swagger-ui.html

