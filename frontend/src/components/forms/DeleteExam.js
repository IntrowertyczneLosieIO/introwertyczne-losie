
import React from "react";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import NewMajorInfo from "./formParts/NewMajorInfo";
import ContactPersonInfo from "./formParts/ContactPersonInfo";
import FormGroup from "react-bootstrap/FormGroup";

class DeleteExam extends React.Component {

    constructor(props) {
        super(props);
        this.formRef = React.createRef();
        this.state = {
            validated: false,
            showConfirmationModal: false,
            userData: this.getInitialState(),
            mixedMapping: {
                "Tak": true,
                "Nie": false
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
        let promise = new Promise(this.handleDelete);
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
                fullName: "",
                shortName: "",
                faculty: "WIEiT",
                mode: "stacjonarne",
                mixedField: "Tak",
                name1: "",
                surname1: "",
                email1: "",
                phone1: "",
                name2: "",
                surname2: "",
                email2: "",
                phone2: "",
                annotations: "",
                numberOfPlaces: ""
            }
        }
    }

    handleDelete = () => {

        this.props.handleHide();
        fetch(`/delete-exam/${this.props.initialInputValues.id}`, {
            method: 'delete'
        })
            .then((response) => response.json())
            .catch((error) => console.log(error));
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
                <Modal show={this.props.show}  onHide={this.hideAndClearState}
                       backdrop={"static"} keyboard={false}>
                    <Modal.Body className={"custom-margins"}>
                        <h4 className={"text-center"}>Czy na pewno chcesz usunąć egzamin ?</h4>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant={"danger"} onClick={this.hideAndClearState}>Nie
                        </Button>
                        <Button variant={"success"} className={"custom-margins"} onClick={this.handleSaveAndOpenConfirm}>
                            Tak
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>
        );
    }
}

export default DeleteExam;