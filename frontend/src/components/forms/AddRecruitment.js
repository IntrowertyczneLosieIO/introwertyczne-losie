import React from "react";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import NewRecruitmentInfo from "./formParts/NewRecruitmentInfo";

class AddRecruitment extends React.Component {
    constructor(props) {
        super(props);
        this.formRef = React.createRef();
        this.state = {
            show: true,
            validated: false,
            showConfirmationModal: false,
            userData: this.getInitialState(),
            semesterMapping: {
                "letni": "summer",
                "zimowy": "winter"
            },
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
                acronym: "",
                recruitmentStatus: "",
                year: "",
                semester: ""
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
            // this.props.handleHide();
            this.state.show = false;
            let userDataToSend = {
                acronym: this.state.userData.acronym,
                recruitmentStatus: this.state.userData.recruitmentStatus,
                year: this.state.userData.year,
                semester: this.state.semesterMapping[this.state.userData.semester]
            }
            console.log(userDataToSend);
            fetch("/new-recruitment", {
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
        this.state.show = false;
        // this.props.handleHide();
    }


    render() {
        // console.log(this.props.show)
        return (
            <>
                <Modal show={this.state.show} dialogClassName={"custom-width-modal"} onHide={this.hideAndClearState}
                       backdrop={"static"} keyboard={false}>
                    <Modal.Header closeButton className={"modal-form-bg-color"}>
                        <Modal.Title className={"custom-margins custom-font text-light"}>Dodawanie nowej rekrutacji</Modal.Title>
                    </Modal.Header>
                    <Modal.Body className={"custom-margins"}>
                        <Form noValidate validated={this.state.validated} ref={this.formRef}>
                            <NewRecruitmentInfo getFormData={this.getFormData} inputValuesFromState={this.state.userData}/>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer className={"modal-form-bg-color"}>
                        <Button variant={"danger"} onClick={this.hideAndClearState}>Anuluj </Button>
                        <Button variant={"success"} className={"custom-margins"} onClick={this.handleSaveAndOpenConfirm}>
                            Dodaj rekrutację
                        </Button>
                    </Modal.Footer>
                </Modal>
                <Modal show={this.state.showConfirmationModal} onHide={this.handleCloseConfirmationModal} size={"lg"}>
                    <Modal.Body>
                        <h4 className={"text-center"}>Rekrutacja została dodana pomyślnie</h4>
                    </Modal.Body>
                    <Modal.Footer className={"modal-form-bg-color"}>
                        <Button variant={"success"} onClick={this.handleCloseConfirmationModal} block size={"sm"}>OK</Button>
                    </Modal.Footer>
                </Modal>
            </>
        );
    }
}

export default AddRecruitment;