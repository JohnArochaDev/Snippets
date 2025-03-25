package org.snippet.Service;

import org.snippet.Modal.Snippet;

import java.util.List;
import java.util.Optional;

public interface SnippetService {
    // Save
    Snippet SaveSnippet(Snippet snippet);

    // Update
    Snippet UpdateSnippet(Integer id, Snippet snippet);

    // Delete
    void DeleteSnippet(Snippet snippet);

    // All
    List<Snippet> findByLanguage(String language);
    List<Snippet> findAll();

    Optional<Snippet> findById(Integer id);

}
