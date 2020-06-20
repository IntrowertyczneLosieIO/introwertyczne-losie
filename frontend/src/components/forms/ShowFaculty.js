import React from "react";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import NewFacultyInfo from "./formParts/NewFacultyInfo";

class ShowFaculty extends React.Component {
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

    hideAndClearState = () => {
        this.setState({
            userData: this.getInitialState()
        });
        this.setValidated(false);
        this.props.handleHide();
    }

    downloadReport = () => {
        fetch(`/report/faculty/${this.props.initialInputValues.id}`)
            .then(response => {
                const filename = response.headers.get('Content-Disposition').split('filename=')[1];
                response.blob().then(blob => {
                    let url = window.URL.createObjectURL(blob);
                    let a = document.createElement('a');
                    a.href = url;
                    a.download = filename;
                    a.click();
                });
            });
    }



    render() {
        return (
            <>
                <Modal show={this.props.show} dialogClassName={"custom-width-modal"} onHide={this.hideAndClearState}
                       backdrop={"static"} keyboard={false}>
                    <Modal.Body className={"custom-margins"}>
                        <Form noValidate validated={this.state.validated} ref={this.formRef}>
                            <NewFacultyInfo getFormData={this.getFormData} inputValuesFromState={this.state.userData}/>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant={"success"} className={"custom-margins"} onClick={this.downloadReport}>
                            Pobierz raport
                        </Button>
                        <Button variant={"info"} className={"custom-margins"} onClick={this.hideAndClearState}>
                            Zamknij
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>
        );
    }
}

export default ShowFaculty;