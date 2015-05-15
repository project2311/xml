/* Copy the file named in arg[0] to the file named in arg[1].   Copyright (c) Gunnar Gotshalks. All Rights Reserved.     Permission to use, copy, modify, and distribute this software   and its documentation for NON-COMMERCIAL purposes and   without fee is hereby granted. */import java.io.*;public class Copy_Examples {static int EOF = -1;static int EOL = '\n';static int Space_char = ' ';static int c;static FileInputStream fis;static FileOutputStream fos;public static void main(String args[]) {//  System.out.println("Copying from " + args[0] + " to " + args[0] + ".copy");//      BufferedReader stdin = new BufferedReader(//                               new FileReader(FileDescriptor.in));  int choice = 0;  try {    File inputFile = new File("input_1.text");    File outputFile = new File("output_1.text");             fis = new FileInputStream(inputFile);    fos = new FileOutputStream(outputFile);        System.out.println("1 Copy character by character");    System.out.println("2 Copy line by line");    System.out.println("3 Copy character by type");    System.out.println("4 Copy with word count");    System.out.println("No other choices");    do {      if (choice != '\n') {        System.out.println("Enter choice number in the range 1..4"); }      choice = System.in.read(); }    while ( choice < '1' || '4' < choice);        switch (choice) {      case '1' : copy_char_by_char();      case '2' : copy_line_by_line();      case '3' : copy_char_by_type();      case '4' : copy_with_word_count();    }        fis.close();    fos.close();       } catch (FileNotFoundException e) {       System.err.println("FileStreamsTest: " + e);  } catch (IOException e) {       System.err.println("FileStreamsTest: " + e);  }}//------------------------------------------------------------------------------static void copy_char_by_char() {  try {    c = fis.read();    while (c != EOF) {      fos.write(c);      c = fis.read();    }  } catch (IOException e) {    System.err.println("Read or write failure in copy_by_char");  }}//------------------------------------------------------------------------------static void copy_line_by_line() {  try {    c = fis.read();    while (c != EOF) {      while (c != EOL) {        fos.write(c);        c = fis.read();      }      fos.write(c);      c = fis.read();    }  } catch (IOException e) {      System.err.println("Read or write failure in copy_by_char");  }}//------------------------------------------------------------------------------static void copy_char_by_type() {  try {    c = fis.read();    while (c != EOF) {      while (c != EOL) {        if (c == Space_char) { fos.write(c); }        else {          if (Character.isDigit(c)) { fos.write(c); }          else {            if (Character.isLetter(c)) { fos.write(c); }            else { fos.write(c); }            }          }        c = fis.read();      }      fos.write(c);      c = fis.read();    }  } catch (IOException e) {      System.err.println("Read or write failure in copy_by_char");  }}//------------------------------------------------------------------------------static void copy_with_word_count() {  int word_count;  try {    c = fis.read();    while (c != EOF) {      copy_space_characters(); // Process leader spaces          if (c == EOL) { // Process a line with no words        fos.write(" -- 0 words".getBytes());        fos.write(c);        c = fis.read();      }      else {        copy_word_characters();   // Process first word in a line        word_count = 1;        while (c != EOL             && c != EOF) { // Process remaining words in a line          copy_space_characters();  // Process inter-word or trailer spaces          if (c != EOL) {  // Had inter-word spaces            copy_word_characters();            word_count += 1;          }        }        // Process end of line that has words in it        fos.write(" -- ".getBytes());        fos.write(new Integer(word_count).toString().getBytes());        fos.write(" word".getBytes());        if (word_count > 1) { fos.write('s'); }        fos.write(c);        c = fis.read();      }    }  } catch (IOException e) {      System.err.println("Read or write failure in copy_by_char");  }}static void copy_space_characters() {  try {    while ( c == Space_char) {      fos.write(c);      c = fis.read();    }  } catch (IOException e) {    System.err.println("Read or write failure in copy_by_char");}}static void copy_word_characters() {  try {    while ( c != Space_char && c != EOL && c != EOF) {      fos.write(c);      c = fis.read();    }  } catch (IOException e) {    System.err.println("Read or write failure in copy_by_char");}}}