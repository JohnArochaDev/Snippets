package org.snippet.Controller;

import org.snippet.Modal.Snippet;
import org.snippet.Service.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/snippets")
public class SnippetController {
    private final SnippetService snippetService;

    @Autowired
    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    // Get all Snippets
    @GetMapping
    public ResponseEntity<List<Snippet>> getAllSnippets() {
        List<Snippet> snippets = snippetService.findAll();
        if (snippets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(snippets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Snippet> updateSnippet(@PathVariable Integer id, @RequestBody Snippet snippet) {
        try{
            Optional<Snippet> foundSnippetOptional = snippetService.findById(id);

            if (foundSnippetOptional.isPresent()) {
                Snippet foundSnippet = foundSnippetOptional.get();
                foundSnippet.setLanguage(snippet.getLanguage());
                foundSnippet.setCode(snippet.getCode());
                Snippet updatedSnippet = snippetService.UpdateSnippet(id, foundSnippet);
                return ResponseEntity.ok(updatedSnippet);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
