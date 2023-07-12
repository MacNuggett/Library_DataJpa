package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dao.BookDao;

import org.example.dao.PersonDao;
import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDao bookDao;
    private final PersonDao personDao;

    @Autowired
    public BooksController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDao.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@ModelAttribute("person")Person person, @PathVariable("id") int id, Model model){
        Book book = bookDao.show(id);
        model.addAttribute("book", book);
        model.addAttribute("oneperson", bookDao.getPerson(book.getPersonId()));
        model.addAttribute("people", personDao.index());
        return "books/show";
    }

    @DeleteMapping("/{id}/delete_person")
    public String deletePerson(@PathVariable("id") int id){
        bookDao.deletePerson(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping
    public String setPerson(@ModelAttribute("person") Person person){

        return"redirect:/books";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("book", new Book());
        return "/books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "/books/new";
        bookDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDao.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, @PathVariable("id") int id,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/edit";
        bookDao.update(id, book);
        return "redirect:/books";
    }

    @PostMapping("/{id}")
    public String setPerson(@ModelAttribute("person") Person person, @PathVariable("id") int bookId){
        bookDao.setPerson(person.getId(), bookId);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDao.delete(id);
        return "redirect:/books";
    }
}
