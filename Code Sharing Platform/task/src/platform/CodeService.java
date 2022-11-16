package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CodeService {
    @Autowired
    CodeRepository codeRepository;

    public Code getCodeById(UUID id) {
        return codeRepository.findCodeById(id);
    }

    public List<Code> latest10() {
        return codeRepository.test();
    }

    public void saveCode(Code code) {
        codeRepository.save(code);
    }
    public void deleteCodeRestriction() {
        codeRepository.deleteByToBeDeletedIsTrue();
    }
}