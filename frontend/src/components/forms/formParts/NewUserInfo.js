import React from "react";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Col from "react-bootstrap/Col";
import Alert from "react-bootstrap/Alert";

class NewUserInfo extends React.Component {

    constructor(props) {
        super(props);
        this.passwordRef = React.createRef();
        this.state = {
            showAddAdminConfirmation: false
        }
    }

    setShowAddAdminConfirmation = (show) => {
        this.setState({
            showAddAdminConfirmation: show
        })
    }



    handleControlChange = (event) => {
        if(event.target.id.startsWith("role")) {
            this.props.getFormData(event.target.id.slice(0, -1), event.target.value);
        } else {
            this.props.getFormData(event.target.id, event.target.value);
        }
    }

    togglePasswordVisibility = () => {
        let passwordRef = this.passwordRef.current;
        if (passwordRef.type === "password") {
            passwordRef.type = "text";
        } else {
            passwordRef.type = "password";
        }
    }

    render() {
        return (
            <>
                <h5 className={"mt-4 text-secondary mb-3"}>Informacje o użytkowniku</h5>
                <Form.Row>
                    <FormGroup as={Col} controlId={"username"}>
                        <Form.Label>Nazwa</Form.Label>
                        <Form.Control type={"text"} onChange={this.handleControlChange} required
                                      value={this.props.inputValuesFromState.username}/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"password"}>
                        <Form.Label>Hasło</Form.Label>
                        <Form.Control type={"password"} onChange={this.handleControlChange} required
                                      value={this.props.inputValuesFromState.password} ref={this.passwordRef}/>
                    </FormGroup>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col} xs={8}>
                        <Form.Check custom inline type={"radio"} label={"Administrator"} name={"group"} id={"role1"} onClick={() => this.setShowAddAdminConfirmation(true)} value={"admin"} onChange={this.handleControlChange}/>
                        <Form.Check custom inline type={"radio"} label={"Użytkownik niezwykły"} name={"group"} id={"role2"} onClick={() => this.setShowAddAdminConfirmation(false)} value={"special_user"} onChange={this.handleControlChange}/>
                        <Form.Check custom inline type={"radio"} label={"Użytkownik zwykły"} name={"group"} id={"role3"} onClick={() => this.setShowAddAdminConfirmation(false)} value={"user"} onChange={this.handleControlChange}/>
                    </Form.Group>
                    <Form.Group as={Col} xs={4} className={"text-right"}>
                        <Form.Check custom inline type={"checkbox"} label={"Pokaż hasło"} id={"check4"} onClick={this.togglePasswordVisibility}/>
                    </Form.Group>
                </Form.Row>
                <Form.Row className={"justify-content-center mb-4"}>
                    <Alert show={this.state.showAddAdminConfirmation} className={"mt-4"} variant={"info"}>Zamierzasz dodać nowego administratora, przemyśl dobrze tę decyzję!</Alert>
                </Form.Row>
            </>
        );
    }
}

export default NewUserInfo;