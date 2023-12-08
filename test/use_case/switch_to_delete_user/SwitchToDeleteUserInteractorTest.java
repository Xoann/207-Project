package use_case.switch_to_delete_user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

public class SwitchToDeleteUserInteractorTest {

    @Mock
    private SwitchToDeleteUserOutputBoundary mockPresenter;

    private SwitchToDeleteUserInteractor interactor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new SwitchToDeleteUserInteractor(mockPresenter);
    }

    @Test
    public void execute_CallsPrepareSuccessView() {
        // Act
        interactor.execute();

        // Assert
        verify(mockPresenter).prepareSuccessView();
    }
}
