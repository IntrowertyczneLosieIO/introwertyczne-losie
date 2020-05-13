import React from "react";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import NewRoomInfo from "./formParts/NewRoomInfo";

class DeleteRoom extends React.Component {
    constructor(props) {
        super(props);
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
                localization: "",
                number: "",
                recommendedCapacity: "",
                maximalCapacity: ""
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

    handleDelete = (resolve, reject) => {

            this.props.handleHide();
            fetch(`/delete-room/${this.props.initialInputValues.id}`, {
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
                <Modal show={this.props.show} dialogClassName={"custom-width-modal"} onHide={this.hideAndClearState}
                       backdrop={"static"} keyboard={false}>
                    <Modal.Body className={"custom-margins"}>
            <h4 className={"text-center"}>Czy na pewno chcesz usunąć salę ?</h4>
        </Modal.Body>
                    <Modal.Footer className={"modal-form-bg-color"}>
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

export default DeleteRoom;
