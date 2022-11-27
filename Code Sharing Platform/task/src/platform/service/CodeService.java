package platform.service;

import org.springframework.stereotype.Service;
import platform.model.Code;
import platform.repository.CodeRepository;
import java.util.List;
import java.util.UUID;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Code getCodeById(UUID id) {
        return codeRepository.findCodeById(id);
    }

    public List<Code> latest10() {
        return codeRepository.notRestricted();
    }

    public void saveCode(Code code) {
        codeRepository.save(code);
    }

    public void deleteCodeRestriction() {
        codeRepository.deleteByToBeDeletedIsTrue();
    }

    public void deleteByID(UUID id) {
        codeRepository.deleteById(id);
    }
}