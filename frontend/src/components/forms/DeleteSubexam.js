
import React from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";


class DeleteSubexam extends React.Component {

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
                subExam: {
                    room: "",
                    date: "",
                    time: ""
                }
            }
        }
    }



    handleDelete = () => {
        this.props.handleHide();
        fetch(`/delete-subexam/${this.props.initialInputValues.id}`, {
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
                        <h4 className={"text-center"}>Czy na pewno chcesz usunąć termin egzaminu?</h4>
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

export default DeleteSubexam;