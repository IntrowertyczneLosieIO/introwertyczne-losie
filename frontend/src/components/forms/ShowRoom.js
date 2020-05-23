import React from "react";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import NewRoomInfo from "./formParts/NewRoomInfo";

class ShowRoom extends React.Component {
    constructor(props) {
        super(props);
        this.formRef = React.createRef();
        this.state = {
            validated: false,
            showConfirmationModal: false,
            userData: this.getInitialState(),
        };
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
                        <Form noValidate validated={this.state.validated} ref={this.formRef}>
                            <NewRoomInfo getFormData={this.getFormData} inputValuesFromState={this.state.userData}/>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant={"info"} className={"custom-margins"} onClick={this.hideAndClearState}>
                            Zamknij
                        </Button>
                        </Modal.Footer>
                    </Modal>
            </>
        );
    }
}

export default ShowRoom;