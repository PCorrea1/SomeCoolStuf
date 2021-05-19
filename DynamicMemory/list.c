/**
 *
 * @author Pablo Correa
 *
 */



#include "list.h"


static struct user *create_user(char *name, int id);
static struct node *create_node(char *name, int id);
static int user_equal(const struct user *user1, const struct user *user2);

/** create_user
 *
 * Helper function that creates a user by allocating memory for it on the heap
 * and initializing with the passed in data.
 *

 *
 * @param the fields of the user struct
 * @return a user struct, return NULL if malloc fails
 */
static struct user *create_user(char *name, int id)
{
    struct user *student = (struct user*)malloc(sizeof(struct user));
    if(!student) {
        free(student);
        return NULL;
    }
    if(name == NULL) {
        student->name = NULL;
    }
    else {
    student->name = malloc(strlen(name) + 1);
    strcpy(student->name, name);
    }
    student->id = id;

    return student;
}


/** create_node
 *
 * Helper function that creates a struct node by allocating memory for it on
 * the heap.
 *
 *
 *
 * @param the fields of the user struct
 * @return a linked list node
 */
static struct node *create_node(char *name, int id)
{
    struct node *temp = (struct node *)malloc(sizeof(struct node));
    if(!temp) {
        free(temp);
        return NULL;
    }
    temp->next = NULL;
    temp->data = create_user(name, id);
    if(temp->data == NULL) {
        free(temp);
        return NULL;
    }
    return temp;

}


/** user_equal
 * Helper function to help compare two user structs.
 *
 *

 *
 *
 *
 * @param the two user structs to be compared
 * @return 1 if equal, 0 otherwise
 */
static int user_equal(const struct user *user1, const struct user *user2)
{
    if(strcmp(user1->name, user2->name) != 0) {
        return 0;
    }
    else {
        if(user1->id == user2->id) {
            return 1;
        }
        else {
            return 0;
        }
    }
}

/** create_list
 *
 * Creates a 'struct linked_list' by allocating memory for it on the heap.
 *
 * If malloc returns NULL, you should return NULL to indicate failure.
 *
 * @return a pointer to a new list or NULL on failure
 */
struct linked_list *create_list(void)
{
    struct linked_list *temp = (struct linked_list*)malloc(sizeof(struct linked_list));
    if(!temp) {
        return NULL;
    }
    temp->head = NULL;
    temp->size = 0;
    return temp;
}

/** push_front
 *
 * Adds the element at the front of the linked list.
 *
 * @param list a pointer to the linked list struct.
 * @param the fields of the user struct
 * @return FAILURE if the linked_list is NULL or if allocating the new node
 *         fails, SUCCESS if successful.
 */
int push_front(struct linked_list *list, char *name, int id)
{
    if(!list) {
        return FAILURE;
    }
    else {
    struct node *newNode = create_node(name, id);
    if(!newNode) {
        return FAILURE;
    }
    newNode->next = list->head;
    list->size++;
    list->head = newNode;

    return SUCCESS;
    }
}

/** push_back
 *
 * Adds the element to the back of the struct linked_list.
 *
 * @param list a pointer to the linked_list struct.
 * @param the fields of the user struct
 * @return FAILURE if the linked list is NULL or if allocating the new node
 *         fails, SUCCESS if successful.
 */
int push_back(struct linked_list *list, char *name, int id)
{
    if(!list) {
        return FAILURE;
    }
    else {
    struct node *newNode = create_node(name, id);
    if(!newNode) {
        return FAILURE;
    }
    struct node *lastNode = list->head;
    newNode->next = NULL;
    if(list->head == NULL) {
        list->size++;
        list->head = newNode;
        return SUCCESS;
    } else {
        list->size++;
        while(lastNode->next != NULL) {
            lastNode = lastNode->next;
        }
        lastNode->next = newNode;
        return SUCCESS;

    }
    }
}

/** add_at_index
 *
 * Add the element at the specified index in the linked list.
 *
 *
 * @param list a pointer to the linked list struct
 * @param index 0-based, starting from the head in the inclusive range [0,size]
 * @param the fields of the user struct
 * @return FAILURE if the index is out of bounds or the linked list is NULL or
 *         malloc fails otherwise  return SUCCESS
 */
int add_at_index(struct linked_list *list, int index, char *name, int id)
{
    if(!list || index > list->size || index < 0) {
        return FAILURE;
    }
    struct node *newNode = create_node(name, id);
    if(!newNode) {
        return FAILURE;
    }
    if(list->size == 0) {
        newNode->next = list->head;
        list->size++;
        list->head = newNode;
        return SUCCESS;
    }
    struct node *current = list->head;
    list->size++;
    while(index - 1 > 0) {
        current = current->next;
        index--;
    }
    newNode->next = current->next;
    current->next = newNode;

    return SUCCESS;
}

/** get
 *
 * Gets the data at the specified index in the linked list
 *
 * @param list a pointer to the linked list struct
 * @param index 0-based, starting from the head.
 * @param dataOut A pointer to a pointer used to return the data from the
 *        specified index in the linked list or NULL on failure.
 * @return FAILURE if dataOut is NULL or index is out of range of the linked
 *         list or the linked list is NULL, SUCCESS otherwise
 */
int get(struct linked_list *list, int index, struct user **dataOut)
{

    if(!list || index < 0 || index >= list->size || !dataOut) {
        return FAILURE;
    }

    struct node *current = list->head;
    while(index > 0) {
        current = current->next;
        index--;
    }
    *dataOut = current->data;


    return SUCCESS;
}

/** contains
 *
 * Traverses the linked list, trying to see if the linked list contains some
 * data. We say the list contains some input if there exists some node with
 * equal data as the input.
 *
 *
 *
 *
 * @param list a pointer to the linked list struct
 * @param data The data, to see if it exists in the linked list
 * @param dataOut A pointer to a pointer used to return the data contained in
 *        the linked list or NULL on failure
 * @return 0 (false) if dataOut is NULL, the list is NULL, or the list does not
 *         contain data, else 1 (true)
 */
int contains(struct linked_list *list, struct user *data, struct user **dataOut)
{
    if(!list || !dataOut) {
        return 0;
    }
    struct node *current = list->head;
    int i = list->size;
    while(i - 1 > 0) {
        if(current->data->name == NULL && data->name == NULL && current->data->id == data->id) {

            *dataOut = current->data;
            return 1;
        }
        if(user_equal(current->data, data)){
            *dataOut = current->data;
            return 1;
        }
        current = current->next;
        i--;
    }
    *dataOut = NULL;
    return 0;
}

/** pop_front
 *
 * Removes the node at the front of the linked list, and returns its data to
 * the program user.
 *
 * @param list a pointer to the linked list.
 * @param dataOut A pointer to a pointer used to return the data in the first
 *        node or NULL if the linked list is NULL or empty
 * @return FAILURE if dataOut is NULL (the linked list is NULL or empty), else
 *         SUCCESS
 */
int pop_front(struct linked_list *list, struct user **dataOut)
{
    if(!list || list->size == 0) {
        return FAILURE;
    }
    else {
     struct node *temp = list->head;
     *dataOut = list->head->data;
     list->head = temp->next;
     free(temp);
     list->size--;
     return SUCCESS;
    }
}

/** pop_back
 *
 * Removes the node at the back of the linked list, and returns its data to the
 * program user.
 *
 * @param list a pointer to the linked list.
 * @param dataOut A pointer to a pointer used to return the data in the last
 *        node or NULL if the linked list is NULL or empty
 * @return FAILURE if dataOut is NULL (the linked list is NULL or empty), else
 *         SUCCESS
 */
int pop_back(struct linked_list *list, struct user **dataOut)
{
    if(!list || list->size == 0) {
        return FAILURE;
    }
    else if (list->head->next == NULL) {
        *dataOut = list->head->data;
        free(list->head);
        list->size--;
        return SUCCESS;
    }
    else {
    struct node *second_last = list->head;
    while (second_last->next->next != NULL) {
        second_last = second_last->next;
    }
        *dataOut = second_last->next->data;
        list->size--;
        free(second_last->next);
        second_last->next = NULL;
        return SUCCESS;
    }
}


/** remove_at_index
 *
 * Remove the element at the specified index in the linked list.
 *
 * @param list a pointer to the linked list structure
 * @param dataOut A pointer to a pointer used to return the data in the last
 *        node or NULL if the linked list is NULL or empty
 * @param index 0-based, starting from the head in the inclusive range
 *        [0,size-1]
 * @return FAILURE if the index is out of bounds, the linked list is NULL or the
 *         dataOut is NULL, otherwise return SUCCESS
 */
int remove_at_index(struct linked_list *list, struct user **dataOut, int index)
{
    if(!list || index >= list->size || index < 0 || !dataOut) {
        return FAILURE;
    }
    if(index == 0) {
        pop_front(list, dataOut);
        return SUCCESS;
    }
    else if(index == list->size - 1) {
        pop_back(list, dataOut);
        return SUCCESS;
    }
    else {
        struct node *temp = list->head;
        for(int i = 0; i < index - 2; i++) {
            temp = temp->next;
        }
        struct node *temp1 = temp->next;
        temp->next = temp1->next;
        *dataOut = temp1->data;
        list->size--;
        free(temp1);
    }

    return SUCCESS;
}

/** empty_list
 *
 * Empties the linked list. After this is called, the linked list should be
 * empty.
 *
 *
 *
 *
 * @param list a pointer to the linked list struct
 */
void empty_list(struct linked_list *list)
{
    if(!list) {
        return ;
    }
    else {
    struct node *temp = list->head;
    while(list->head != NULL) {
        list->head = list->head->next;
        free(temp->data->name);
        //free(temp->data->id);
        free(temp->data);
        free(temp);
        list->size--;
        temp = list->head;
       }
    }
}


/** replace_name_at_index
 *
 * Replaces the name field in the user data struct of the node at the specified
 * index with a new name
 *
 *
 *
 * @param list a pointer to the linked list struct
 * @param index 0-based, starting from the head in the inclusive range
 *        [0,size-1]
 * @param newName a pointer to the new name
 * @return FAILURE if the index is out of bounds, the linked list is NULL or
 *         realloc fails otherwise return SUCCESS
 */
int replace_name_at_index(struct linked_list *list, int index, char *newName)
{
    if(!list || index < 0 || index >= list->size) {
        return FAILURE;
    }
    else {
        struct node *current = list->head;
        while(index > 0) {
            current = current->next;
            index--;
        }
        char *newNameNode = realloc(current->data->name, strlen(newName) + 1);
        if(!newNameNode) {
            return FAILURE;
        }
        current->data->name = newNameNode;
        strcpy(current->data->name, newName);
        return SUCCESS;
    }
}
