**The Ticket4sale project**
 
 This is the imaginary project for imaginary ticket4sale company.
 
 In this project I used different technologies and programming language which are mentioned bellow:
 - Java 8
 - Spring Boot
 - Maven
 - Spring REST
 - Junit & Mockito
 - HTML, Bootstrap, and ReactJS
 
 In this project, we have both a CLI tool, and a web application. As it is requested the CLI tool gets 3 parameters, a CSV file path, Query date, and show date. But the web application only get the show date and uses default CSV file path and query date.
 
 I tried to make some features of the application configurable. For changing the configuration pleas open the application.properties file and change the wanted property.
  The most important property which it's changing is necessary is "DEFAULT_SHOW_CSV_PATH", The value of it should be your local path of the CSV file.
    
 For running the application, please build it via Maven, then run the result Jar file. For using the CLI tool, please put all 3 needed arguments, I mean the CSV file path, the Query date, and the show date. Please use ISO format for all dates.
  When the project starts, the web application works implicitly, and it reads automatically the CSV file, and makes the asked report as JSON structure, then print it in the Console.
   
   For using the web application, please use http://localhost:8080 in your browser. Although my experience in FrontEnd is not very competitive, and it was the first time I used ReactJs, but I did it somehow. Please only put the show date in the text box and click the button. Please only use YYYY-MM-DD format.
     The program returns the result data, notice is that the price and the hall will be represented if the status of the show is OPEN_FOR_SALE
      
 In this project I create some different kind of classes, some of them are Entity classes, which are related to data structure of the domain, some others are Service classes, which are the main part of the business logic of each domain, some others are classes for completing the business logic, such as an interface, and two classes for a Strategy Design pattern for finding price of a show, and a Factory class for instantiating the Strategy. Moreover, I mad DTO classes, for creating data structures which are used in generatin JSON objects, furthermore, two REST controller and, one Util for reading data from Property file class are there.
   
   In addition, I mad some Unit tests via Junit, and Mockito for testing my units of application.
   
   I am thankful for the great assessment task, I really enjoyed it, I hope you will enjoy my solutions too.
     Please feel free to ask any question about my solutions.
     
     Yours sincerely
     Mohammadreza Mirali