package use_case.signup;

import entity.User;
import entity.UserFactory;
<<<<<<< HEAD

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
=======
import use_case.recommendation.RecommendationUserDataAccessInterface;

public class SignupInteractor implements SignupInputBoundary {
    final RecommendationUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(RecommendationUserDataAccessInterface signupDataAccessInterface,
>>>>>>> dev
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
<<<<<<< HEAD
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("Username already taken.");
        } else {
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
=======
        if (userDataAccessObject.existsByUsername(signupInputData.getUsername())) {
            userPresenter.prepareFailView("Username already taken.");
        } else {
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), signupInputData.getApiKey());
>>>>>>> dev
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}