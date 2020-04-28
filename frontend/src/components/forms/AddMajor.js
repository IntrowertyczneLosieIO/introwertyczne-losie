import React from "react";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Button from "react-bootstrap/Button";
import ContactPersonInfo from "./formParts/ContactPersonInfo";
import NewMajorInfo from "./formParts/NewMajorInfo";

class AddMajor extends React.Component {
    constructor(props) {
        super(props);
        this.formRef = React.createRef();
        this.state = {
            validated: false,
            showConfirmationModal: false,
            userData: this.getInitialState()
        };
    }

    setShowConfirmationModal = (show) => {
        this.setState({
            showConfirmationModal: show
        });
    }

    handleCloseConfirmationModal = () => this.setShowConfirmationModal(false);
    handleOpenConfirmationModal = () => this.setShowConfirmationModal(true);

    handleSaveAndOpenConfirm = () => {
        let promise = new Promise(this.handleSave);
        promise.then(
            this.handleOpenConfirmationModal
        ).catch(
            () => {console.log("err")}
        )
    }

    setValidated = (validated) => {
        this.setState({
            validated
        });
    }

    getInitialState = () => {
        return {
            major: "",
            faculty: "1",
            modeOfStudy: "stacjonarne",
            type: "pisemny",
            mixed: "Tak",
            name1: "",
            surname1: "",
            email1: "",
            phone1: "",
            name2: "",
            surname2: "",
            email2: "",
            phone2: "",
            annotations: ""
        }
    }

    handleInputChange = (event) => {
        let currentUserData = this.state.userData;
        this.setState({
            userData: {
                ...currentUserData,
                [event.target.id]: event.target.value
            }
        });
    }

    getFormData = (target, value) => {
        let currentUserData = this.state.userData;
        this.setState({
            userData: {
                ...currentUserData,
                [target]: value
            }
        })
    }

    handleSave = (resolve, reject) => {
        let form = this.formRef.current;
        if (!form.checkValidity()) {
            this.setValidated(true);
            reject();
        }
        else {
            this.props.handleHide();
            let userDataToSend = {
                faculty: "WIEiT",
                fullName: this.state.userData.major,
                shortName: this.state.userData.major,
                mode: "fullTime",
                numberOfPlaces: 100,
                contactPerson1: this.state.userData.name1 + " " + this.state.userData.surname1,
                contactPerson2: this.state.userData.name2 + " " + this.state.userData.surname2,
                mixedField: true,
                annotations: this.state.userData.annotations
            }
            fetch("/new-major", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(userDataToSend)
            })
                .then((response) => response.json())
                .then(() => {
                    this.setState({
                        userData: this.getInitialState()
                    });
                    this.setValidated(false);
                    resolve();
                })
                .catch((error) => console.log(error));
        }
    }

    hideAndClearState = () => {
        this.setState({
            userData: this.getInitialState()
        });
        this.setValidated(false);
        this.props.handleHide();
    }

    render() {
        return (
            <>
            <Modal show={this.props.show} dialogClassName={"custom-width-modal"} onHide={this.hideAndClearState}
                   backdrop={"static"} keyboard={false}>
                <Modal.Header closeButton>
                    <Modal.Title className={"custom-margins"}>Dodawanie nowego kierunku</Modal.Title>
                </Modal.Header>
                <Modal.Body className={"custom-margins"}>
                    <Form noValidate validated={this.state.validated} ref={this.formRef}>
                        <NewMajorInfo options={this.props.options} getFormData={this.getFormData}/>
                        <ContactPersonInfo order={1} getFormData={this.getFormData}/>
                        <ContactPersonInfo order={2} getFormData={this.getFormData}/>
                        <h4 className={"mt-3 text-center"}>Uwagi</h4>
                        <FormGroup controlId={"annotations"}>
                            <Form.Control as={"textarea"} rows={"4"} onChange={this.handleInputChange}/>
                        </FormGroup>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant={"primary"} className={"custom-margins"} onClick={this.handleSaveAndOpenConfirm}>
                        Dodaj kierunek
                    </Button>
                </Modal.Footer>
            </Modal>
                <Modal show={this.state.showConfirmationModal} onHide={this.handleCloseConfirmationModal}>
                    <Modal.Body>
                        <h4 className={"text-center"}>Kierunek został dodany pomyślnie</h4>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant={"success"} onClick={this.handleCloseConfirmationModal}>OK</Button>
                    </Modal.Footer>
                </Modal>
            </>
        );
    }
}

export default AddMajor;