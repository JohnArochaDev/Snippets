package org.snippet.Service;

import org.snippet.Modal.Snippet;
import org.snippet.Repository.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SnippetServiceImpl implements SnippetService {

    private final SnippetRepository snippetRepository;

    @Autowired
    public SnippetServiceImpl(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    @Override
    public Snippet SaveSnippet(Snippet snippet) {
        return snippetRepository.save(snippet);
    }

    @Override
    public Snippet UpdateSnippet(Integer id, Snippet snippet) {
        Optional<Snippet> snippetOptional = snippetRepository.findById(id);
        if (snippetOptional.isPresent()) {
            Snippet updatedSnippet = snippetOptional.get();
            updatedSnippet.setCode(snippet.getCode());
            updatedSnippet.setLanguage(snippet.getLanguage());
            return snippetRepository.save(updatedSnippet);
        } else {
            throw new RuntimeException("Snippet not found");
        }
    }

    @Override
    public void DeleteSnippet(Snippet snippet) {
        Optional<Snippet> optionalSnippet = snippetRepository.findById(snippet.getId());
        if (optionalSnippet.isPresent()) {
            snippetRepository.delete(snippet);
        } else {
            throw new RuntimeException("Snippet not found.");
        }
    }

    @Override
    public List<Snippet> findAll() {
        List<Snippet> foundSnippets = snippetRepository.findAll();
        if (foundSnippets.isEmpty()) {
            throw new RuntimeException("Snippets not found");
        } else {
            return foundSnippets;
        }
    }

    @Override
    public List<Snippet> findByLanguage(String language) {
        List<Snippet> foundSnippets = snippetRepository.findByLanguage(language);
        if (foundSnippets.isEmpty()) {
            throw new RuntimeException("Snippets not found");
        } else {
            return foundSnippets;
        }
    }
}
