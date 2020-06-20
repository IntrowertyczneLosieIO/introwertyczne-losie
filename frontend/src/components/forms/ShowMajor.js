
import React from "react";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import NewMajorInfo from "./formParts/NewMajorInfo";
import ContactPersonInfo from "./formParts/ContactPersonInfo";
import FormGroup from "react-bootstrap/FormGroup";

class ShowMajor extends React.Component {

    constructor(props) {
        super(props);
        this.formRef = React.createRef();
        this.state = {
            validated: false,
            showConfirmationModal: false,
            userData: this.getInitialState(),
            faculties: ["WGiG", "WIMIP", "WEAiIB", "WIEiT", "WIMiR", "WGGiOS", "WGGiIS", "WIMiC", "WO", "WMN", "WWNiG", "WZ", "WEiP", "WFiIS", "WMS", "WH"],
            modesMapping: {
                "stacjonarne": "fullTime",
                "niestacjonarne": "partTime"
            },
            mixedMapping: {
                "Tak": true,
                "Nie": false
            }
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

    getFormData = (target, value) => {
        let currentUserData = this.state.userData;
        this.setState({
            userData: {
                ...currentUserData,
                [target]: value
            }
        })
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


    hideAndClearState = () => {
        this.setState({
            userData: this.getInitialState()
        });
        this.setValidated(false);
        this.props.handleHide();
    }

    downloadReportPDF = () => {
        fetch(`/report/major/pdf/${this.props.initialInputValues.id}`)
            .then(response => {
                const filename =  response.headers.get('Content-Disposition').split('filename=')[1];
                response.blob().then(blob => {
                    let url = window.URL.createObjectURL(blob);
                    let a = document.createElement('a');
                    a.href = url;
                    a.download = filename;
                    a.click();
                });
            });
    }

    downloadReportMD = () => {
        fetch(`/report/major/md/${this.props.initialInputValues.id}`)
            .then(response => {
                const filename =  response.headers.get('Content-Disposition').split('filename=')[1];
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
                            <NewMajorInfo getFormData={this.getFormData} faculties={this.state.faculties} inputValuesFromState={this.state.userData}/>
                            <ContactPersonInfo order={1} getFormData={this.getFormData} inputValuesFromState={this.state.userData}/>
                            <ContactPersonInfo order={2} getFormData={this.getFormData} inputValuesFromState={this.state.userData}/>
                            <h5 className={"mt-4 text-secondary mb-3"}>Uwagi</h5>
                            <FormGroup controlId={"annotations"}>
                                <Form.Control as={"textarea"} rows={"4"} onChange={this.handleInputChange}/>
                            </FormGroup>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant={"success"} className={"custom-margins"} onClick={this.downloadReportPDF}>
                            Pobierz raport PDF
                        </Button><Button variant={"success"} className={"custom-margins"} onClick={this.downloadReportMD}>
                            Pobierz raport Markdown
                        </Button>
                        <Button variant={"info"} className={"custom-margins"} onClick={this.hideAndClearState}>
                            Zamknij
                        </Button>
                    </Modal.Footer >
                </Modal>
            </>
        );
    }
}

export default ShowMajor;