import React from "react";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import NewFacultyInfo from "./formParts/NewFacultyInfo";

class AddFaculty extends React.Component {
    constructor(props) {
        super(props);
        this.formRef = React.createRef();
        this.state = {
            validated: false,
            showConfirmationModal: false,
            userData: this.getInitialState(),
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
        if (this.props.initialInputValues) {
            return this.props.initialInputValues;
        }
        else {
            return {
                name: "",
                acronym: ""
            }
        }
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
                name: this.state.userData.name,
                acronym: this.state.userData.acronym
            }
            console.log(userDataToSend);
            fetch("/new-faculty", {
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
                    <Modal.Header closeButton className={"modal-form-bg-color"}>
                        <Modal.Title className={"custom-margins custom-font text-light"}>Dodawanie nowego wydziału</Modal.Title>
                    </Modal.Header>
                    <Modal.Body className={"custom-margins"}>
                        <Form noValidate validated={this.state.validated} ref={this.formRef}>
                            <NewFacultyInfo getFormData={this.getFormData} inputValuesFromState={this.state.userData}/>
                        </Form>l
                    </Modal.Body>
                    <Modal.Footer className={"modal-form-bg-color"}>
                        <Button variant={"danger"} onClick={this.hideAndClearState}>Anuluj
                        </Button>
                        <Button variant={"success"} className={"custom-margins"} onClick={this.handleSaveAndOpenConfirm}>
                            Dodaj wydział
                        </Button>
                    </Modal.Footer>
                </Modal>
                <Modal show={this.state.showConfirmationModal} onHide={this.handleCloseConfirmationModal} size={"lg"}>
                    <Modal.Body>
                        <h4 className={"text-center"}>Wydział został dodany pomyślnie</h4>
                    </Modal.Body>
                    <Modal.Footer className={"modal-form-bg-color"}>
                        <Button variant={"success"} onClick={this.handleCloseConfirmationModal} block size={"sm"}>OK</Button>
                    </Modal.Footer>
                </Modal>
            </>
        );
    }
}

export default AddFaculty;