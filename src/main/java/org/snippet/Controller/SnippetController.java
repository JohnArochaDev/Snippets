package org.snippet.Controller;

import static org.snippet.Security.Encryption.AesEncryptionUtil.encrypt;
import static org.snippet.Security.Encryption.AesEncryptionUtil.decrypt;
import static org.snippet.Util.SnippetMapper.convertToDto;
import static org.snippet.Util.SnippetMapper.toDTOList;

import org.snippet.Modal.Dto.SnippetDTO;
import org.snippet.Modal.Snippet;
import org.snippet.Modal.User;
import org.snippet.Service.Snippet.SnippetService;
import org.snippet.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/snippets")
public class SnippetController {
    private final SnippetService snippetService;
    private final UserService userService;
    private final String secretKey;

    @Autowired
    public SnippetController(SnippetService snippetService, UserService userService) {
        this.snippetService = snippetService;
        this.userService = userService;
        this.secretKey = System.getenv("ENCRYPTED_KEY");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<SnippetDTO>> getAllSnippets() throws Exception {
        List<Snippet> snippets = snippetService.findAll();
        if (snippets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Snippet snippet : snippets) {
            snippet.setCode(decrypt(snippet.getCode(), secretKey));
        }
        return ResponseEntity.ok(toDTOList(snippets));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<SnippetDTO> getSnippetById(@PathVariable Integer id) throws Exception {
        Optional<Snippet> snippet = snippetService.findById(id);
        if (snippet.isPresent()) {
            Snippet foundSnippet = snippet.get();
            foundSnippet.setCode(decrypt(foundSnippet.getCode(), secretKey));
            return ResponseEntity.ok(convertToDto(foundSnippet));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<SnippetDTO> updateSnippet(@PathVariable Integer id, @RequestBody Snippet snippet) {
        try {
            Optional<Snippet> foundSnippetOptional = snippetService.findById(id);

            if (foundSnippetOptional.isPresent()) {
                Snippet foundSnippet = foundSnippetOptional.get();
                foundSnippet.setLanguage(snippet.getLanguage());
                foundSnippet.setCode(encrypt(snippet.getCode(), secretKey));
                Snippet updatedSnippet = snippetService.updateSnippet(id, foundSnippet);
                return ResponseEntity.ok(convertToDto(updatedSnippet));
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<SnippetDTO> createSnippet(@RequestBody Snippet snippet) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Authenticated user not found"));
        snippet.setUser(user);
        snippet.setCode(encrypt(snippet.getCode(), secretKey));
        Snippet newSnippet = snippetService.saveSnippet(snippet);

        return new ResponseEntity<>(convertToDto(newSnippet), HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
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