package com.kenzie.app;

// import necessary libraries

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /* Java Fundamentals Capstone project:
       - Define as many variables, properties, and methods as you decide are necessary to
       solve the program requirements.
       - You are not limited to only the class files included here
       - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
         definition provided
       - Your program execution must run from the main() method in Main.java
       - The rest is up to you. Good luck and happy coding!
     */

    static final String URL = "https://jservice.kenzie.academy/api/clues";
    static int counter = 0;
    static int score = 0;

    static ArrayList<String> questionsList = new ArrayList<>();
    static ArrayList<String> categoryList = new ArrayList<>();
    static ArrayList<String> answerList = new ArrayList<>();
    static String answer;
    static String question;
    static String category;

    //choose question & category
    public static String randomQuestion() {

        int max = 100;
        int min = 1;
        int range = max - min + 1;

        int randInt = (int) (Math.random() * range) + min;

        answer = answerList.get(randInt);
        question = questionsList.get(randInt);
        category = categoryList.get(randInt);

        return "Category: " + categoryList.get(randInt) + "\n" + "Question: " + questionsList.get(randInt) + "\n";
    }

    //getQuestion from HTTP request
    public static void getQuestion() {

        String httpResponse = CustomHttpClient.sendGET(URL);
        ObjectMapper objectMapper = new ObjectMapper();
        QuestionsListDTO QuestionsDTOList = null;

        try {
            QuestionsDTOList = objectMapper.readValue(httpResponse, QuestionsListDTO.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        for (QuestionsDTO question : QuestionsDTOList.getClues()) {
            questionsList.add(question.getQuestion());
            categoryList.add(question.getCategory().getTitle());
            answerList.add(question.getAnswer());
        }
    }

    //Display question and get user input
    public static void displayQuestion() {

        System.out.println("You will be presented with 10 random trivia questions. For each correct answer, you will get 1 point. \n");

        while (counter < 10) {
            System.out.println(randomQuestion());
            counter++;
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your answer:");

            String userInput = scan.nextLine();

            if (userInput.equalsIgnoreCase(answer.toLowerCase())) {
                System.out.println("\nCorrect!");
                score++;
                System.out.println("Score: " + score + "\n");
            } else {
                System.out.println("\nNot quite! The correct answer is: " + answer);
                System.out.println("Your score: " + score + "\n");
            }

            if (counter == 10) {
                System.out.println("Thank you for taking the time to answer some trivia questions! You got the right answer for " + score + " questions.");
                System.out.println("Your total score: " + score);
            }
        }
    }

    public static void main(String[] args) {

        getQuestion();
        displayQuestion();
    }
}