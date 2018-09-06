package ngom.sys;

import ngom.base.page.Pagination;
import ngom.domain.sys.SensitiveWords_C;
import ngom.dto.sys.Sensitive;

public interface SensitiveWordsService {
    Pagination<SensitiveWords_C> findSensitiveWordsList(Sensitive sensitive);

    void deleteSensitiveWords(Sensitive sensitive);

    void addSensitiveWords(String sensitive);
}
