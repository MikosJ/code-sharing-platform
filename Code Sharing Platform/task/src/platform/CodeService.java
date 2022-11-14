package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {
    @Autowired
    CodeRepository codeRepository;

    public Code getCodeById(Long id) {
        return codeRepository.findCodeById(id);
    }

    public List<Code> latest10() {
        return codeRepository.findFirst10ByOrderByIdDesc();
    }

    public void saveCode(Code code) {
        //code.setId(0L);
        codeRepository.save(code);
    }
}