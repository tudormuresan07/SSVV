package ssvv.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import java.util.Iterator;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    private Service service;

    private Service before(){
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme_test.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note_test.xml");

        return new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test__saveStudent__addValidStudent__studentCorrectlyAdded(){
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        int result=service.saveStudent("1","Gion",935);
        assertEquals(0,result);
    }

    @Test
    public void test__saveStudent__addInvalidStudent__studentNotAdded(){
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        int result=service.saveStudent("1","Gion",935);
        assertEquals(1, result);
    }

    @Test
    public void test__saveStudent__addStudentWithEmptyId__studentNotAdded(){
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        int result=service.saveStudent("","Gion",935);
        assertEquals(1, result);
    }

    @Test
    public void test__saveStudent__addStudentWithEmptyName__studentNotAdded(){
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        int result=service.saveStudent("2","",935);
        assertEquals(1, result);
    }

    @Test
    public void test__saveStudent__addStudentWithInvalidGroup__studentNotAdded(){
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        int result=service.saveStudent("3","Gion",1);
        assertEquals(1, result);
    }

    @Test
    public void test__saveTema__addValidTema__temaCorrectlyAdded(){
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme_test.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        int result=service.saveTema("1","Description1",10,3);
        assertEquals(1, result);
    }

    @Test
    public void test__saveTema__addInvalidTema__temaNotSaved(){
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator,"teme_test.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        int result=service.saveTema("1","",10,3);
        assertEquals(1, result);
    }

    @Test
    public void test__saveNota__addValidNota__notaSuccessfullyAdded(){
        Service service=before();

        int result=service.saveNota("1","1",1,13,"Foarte rau");
        assertEquals(-1,result);
    }

    @Test
    public void test__saveEverything(){
        Service service=before();

        int saveStudent=service.saveStudent("2","StudentEminent",936);
        int saveTema=service.saveTema("2","Description2",6,4);
        int saveNota=service.saveNota("2","2",1,10,"Bad");

        assertEquals(1,saveStudent);
        assertEquals(1,saveTema);
        assertEquals(1,saveNota);

    }

    @Test
    public void test__saveTema__temaHavingNullId__temaNotAdded(){
        Service service=before();

        int result=service.saveTema(null,"des",10,2);
        assertEquals(1,result);
    }

    @Test
    public void test__saveTema__temaHavingEmptyId__temaNotAdded(){
        Service service=before();

        int result=service.saveTema("","des",10,2);
        assertEquals(1,result);
    }

    @Test
    public void test__saveTema__temaHavingInvalidDescription__temaNotAdded(){
        Service service=before();

        int result=service.saveTema("10","",10,2);
        assertEquals(1,result);
    }

    @Test
    public void test__saveTema__temaHavingNegativeDeadline__temaNotAdded(){
        Service service=before();

        int result=service.saveTema("10","des",-2,2);
        assertEquals(1,result);
    }

    @Test
    public void test__saveTema__temaHavingDeadlinHigherThanPossible__temaNotAdded(){
        Service service=before();

        int result=service.saveTema("10","des",15,2);
        assertEquals(1,result);
    }

    @Test
    public void test__saveTema__temaHavingNegativePrimire__temaNotAdded(){
        Service service=before();

        int result=service.saveTema("10","des",10,-2);
        assertEquals(1,result);
    }

    @Test
    public void test__saveTema__temaHavingPrimireHigherThanPossible__temaNotAdded(){
        Service service=before();

        int result=service.saveTema("10","des",10,15);
        assertEquals(1,result);
    }

    @Test
    public void test__saveStudentAndTema__saveValidStudentAndTema__studentAndTemaCorrectlyAdded(){
        Service service=before();

        int saveStudentResult=service.saveStudent("100","Ionut",934);
        int saveTemaResult=service.saveTema("100","descriere",10,4);

        assertEquals(1,saveStudentResult);
        assertEquals(1,saveTemaResult);
    }
}
