package org.snippet.Repository;
import org.snippet.Modal.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SnippetRepository extends JpaRepository<Snippet, Integer> {
    List<Snippet> findByLanguage(String language);
}
