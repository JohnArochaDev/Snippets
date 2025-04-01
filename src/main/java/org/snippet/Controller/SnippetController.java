package org.snippet.Controller;

import static org.snippet.Security.AesEncryptionUtil.encrypt;
import static org.snippet.Security.AesEncryptionUtil.decrypt;

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

    private final String secretKey = System.getenv("ENCRYPTED_KEY");

    @Autowired
    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    // Get all Snippets
    @GetMapping
    public ResponseEntity<List<Snippet>> getAllSnippets() throws Exception {
        List<Snippet> snippets = snippetService.findAll();
        if (snippets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Snippet snippet : snippets) {
            snippet.setCode(decrypt(snippet.getCode(), secretKey));
        }
        return ResponseEntity.ok(snippets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Snippet> getSnippetById(@PathVariable Integer id) throws Exception {
        Optional<Snippet> snippet = snippetService.findById(id);
        if (snippet.isPresent()) {
            Snippet foundSnippet = snippet.get();
            foundSnippet.setCode(decrypt(foundSnippet.getCode(), secretKey));
            return ResponseEntity.ok(foundSnippet);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Snippet> updateSnippet(@PathVariable Integer id, @RequestBody Snippet snippet) {
        try {
            Optional<Snippet> foundSnippetOptional = snippetService.findById(id);

            if (foundSnippetOptional.isPresent()) {
                Snippet foundSnippet = foundSnippetOptional.get();
                foundSnippet.setLanguage(snippet.getLanguage());
                foundSnippet.setCode(encrypt(snippet.getCode(), secretKey));
                Snippet updatedSnippet = snippetService.updateSnippet(id, foundSnippet);
                return ResponseEntity.ok(updatedSnippet);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Snippet> createSnippet(@RequestBody Snippet snippet) throws Exception {
        snippet.setCode(encrypt(snippet.getCode(), secretKey));
        Snippet newSnippet = snippetService.saveSnippet(snippet);

        return new ResponseEntity<>(newSnippet, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Snippet> deleteSnippet(@PathVariable Integer id) {
        Optional<Snippet> snippetOptional = snippetService.findById(id);
        if (snippetOptional.isPresent()) {
            snippetService.deleteSnippet(snippetOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}