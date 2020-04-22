package com.jordanmadrigal.notesappplus.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {

    private static final String TIMESTAMP_1 = "05-2019";
    private static final String TIMESTAMP_2 = "04-2019";

    //Compare two equal notes

    @Test
    void isNotesEqual_identicalProperties_returnTrue() throws Exception {
        //Arrange
        Note note1 = new Note(1, "Note # 1", "This is note 1", TIMESTAMP_1);
        Note note2 = new Note(1, "Note # 1", "This is note 1", TIMESTAMP_1);

        //Act

        //Assert
        assertEquals(note1, note2);
        System.out.println("The notes are equal");
    }


    //Compare notes with 2 different ids

    @Test
    void isNotesEqual_differentIds_returnFalse() throws Exception {
        //Arrange
        Note note1 = new Note(1, "Note # 1", "This is note 1", TIMESTAMP_1);
        Note note2 = new Note(2, "Note # 1", "This is note 1", TIMESTAMP_1);
        //Act

        //Assert
        assertNotEquals(note1, note2);
        System.out.println("Notes are note equal. They have different Ids.");
    }


    //Compare two notes with different timestamps

    @Test
    void isNotesEqual_differentTimstamps_returnTrue() throws Exception {
        //Arrange
        Note note1 = new Note(1, "Note # 1", "This is note 1", TIMESTAMP_1);
        Note note2 = new Note(1, "Note # 1", "This is note 1", TIMESTAMP_2);
        //Act

        //Assert
        assertEquals(note1, note2);
        System.out.println("Notes are equal. They have different timestamps");
    }


    //Compare two notes with different titles
    @Test
    void isNotesEqual_differentTitles_returnFalse() throws Exception {
        //Arrange
        Note note1 = new Note(1, "Note # 1", "This is note 1", TIMESTAMP_1);
        Note note2 = new Note(1, "Note # 2", "This is note 1", TIMESTAMP_2);
        //Act

        //Assert
        assertNotEquals(note1, note2);
        System.out.println("Notes are not equal. They have different titles");
    }

    //Compare two notes with different content
    @Test
    void isNotesEqual_differentContent_returnFalse() throws Exception {
        //Arrange
        Note note1 = new Note(1, "Note # 1", "This is note 1", TIMESTAMP_1);
        Note note2 = new Note(1, "Note # 1", "This is note 2", TIMESTAMP_2);
        //Act

        //Assert
        assertNotEquals(note1, note2);
        System.out.println("Notes are not equal. They have different content");
    }
}
