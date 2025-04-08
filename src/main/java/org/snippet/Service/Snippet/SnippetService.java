package org.snippet.Service.Snippet;

import org.snippet.Modal.Snippet;

import java.util.List;
import java.util.Optional;

public interface SnippetService {
    // Save
    Snippet saveSnippet(Snippet snippet);

    // Update
    Snippet updateSnippet(Integer id, Snippet snippet);

    // Delete
    void deleteSnippet(Snippet snippet);

    // All
    List<Snippet> findByLanguage(String language);
    List<Snippet> findAll();
    Optional<Snippet> findById(Integer id);

}
