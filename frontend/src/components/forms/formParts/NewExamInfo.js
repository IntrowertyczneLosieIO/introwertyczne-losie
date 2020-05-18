import React from "react";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Col from "react-bootstrap/Col";

class NewExamInfo extends React.Component {
    constructor(props) {
        super(props);
        this.formRef = React.createRef();
        this.state = {
            showAddNewSubexam: false,
        }
    }

    handleControlChange = (event) => {
        this.props.getFormData(event.target.id, event.target.value);
    }

    setShowAddNew = (show) => {
        this.setState({
            showAddNewSubexam: show
        });
    }

    handleShowSubexam = () => this.setShowAddNew(true);
    handleHideSubexam = () => this.setShowAddNew(false);

    render() {
        let majorList = [];
        majorList = this.props.majors.map((major, index) => {
            return <option key={index} value={major}>{major.fullName}</option>
        });

        return (
            <>
                <h5 className={"mt-4 text-secondary mb-3"}>Informacje o egzaminie</h5>
                <Form.Row>
                    <FormGroup as={Col} controlId={"name"}>
                        <Form.Label>Egzamin</Form.Label>
                        <Form.Control type={"text"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"major"}>
                        <Form.Label>Kierunek</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required>
                            {majorList}
                        </Form.Control>
                    </FormGroup>
                </Form.Row>
                <Form.Row>
                    <FormGroup as={Col} controlId={"startDate"}>
                        <Form.Label>Data rozpoczęcia</Form.Label>
                        <Form.Control type={"date"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"endDate"}>
                        <Form.Label>Data zakończenia</Form.Label>
                        <Form.Control type={"date"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                </Form.Row>
            </>
        );
    }
}

export default NewExamInfo;
