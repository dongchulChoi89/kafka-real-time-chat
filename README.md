# kafka-real-time-chat
## Summary
> Built and published Real time Chat Microservice which uses Kafka as data pipeline<br>
> Used Java, Spring Boot, Kafka, STOMP over WebSocket

## Structure
![Screenshot](structure.png)<br>

## How to run project
> 1st. Run Kafka<br> 
&nbsp;&nbsp;&nbsp;&nbsp;Inside the bin directory in your Kafka directory,<br>
&nbsp;&nbsp;&nbsp;&nbsp;./zookeeper-server-start.sh ../config/zookeeper.properties<br>
&nbsp;&nbsp;&nbsp;&nbsp;./kafka-server-start.sh ../config/server.properties<br>
&nbsp;&nbsp;&nbsp;&nbsp;./kafka-topics.sh --create --topic kafka-chat -zookeeper localhost:2181 --replication-factor 1 --partitions 1<br><br>
> 2nd. Run Kafka real time chat application <br>
&nbsp;&nbsp;&nbsp;&nbsp;mvn spring-boot:run<br><br>
> 3rd. Run Client<br>
&nbsp;&nbsp;&nbsp;&nbsp;Inside the chat-ui directory,<br>
&nbsp;&nbsp;&nbsp;&nbsp;npm start



