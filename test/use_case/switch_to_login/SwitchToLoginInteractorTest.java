package use_case.switch_to_login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

public class SwitchToLoginInteractorTest {

    @Mock
    private SwitchToLoginOutputBoundary mockPresenter;

    private SwitchToLoginInteractor interactor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new SwitchToLoginInteractor(mockPresenter);
    }

    @Test
    public void execute_CallsPrepareSuccessView() {
        // Act
        interactor.execute();

        // Assert
        verify(mockPresenter).prepareSuccessView();
    }
}
